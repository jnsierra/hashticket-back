package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.TicketClient;
import co.ud.hashticket.dto.FieldType;
import co.ud.hashticket.dto.FilterRequest;
import co.ud.hashticket.dto.Operator;
import co.ud.hashticket.dto.SearchRequest;
import co.ud.hashticket.rest.services.*;
import co.ud.hashticket.security.service.UserLoggerService;
import co.ud.ud.hashticket.dto.TicketDto;
import co.ud.ud.hashticket.dto.TicketViewDto;
import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import co.ud.ud.hashticket.dto.ZoneDto;
import co.ud.ud.hashticket.dto.responses.GenericQuery;
import co.ud.ud.hashticket.dto.responses.GenericResponse;
import co.ud.ud.hashticket.dto.ticket.BuyTicket;
import co.ud.ud.hashticket.dto.ticket.ConfirmBuyTicket;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import co.ud.ud.hashticket.exception.BusinessException;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
    private ZoneConfigEventService zoneConfigEventService;
    private ZoneService zoneService;
    private TicketClient ticketClient;
    private UserLoggerService userLoggerService;
    private EmailService emailService;
    private QRGeneratorService qRGeneratorService;
    private GenerateTemplatesEmailServiceImpl generateTemplatesEmailService;
    private final UnaryOperator<String> getUserAuth = x -> userLoggerService.getUserLogger();
    private final BiFunction<Long, Long, Set<ZoneConfigEventDto>> functionGenerateTickets = (eventId, presentationId) -> zoneConfigEventService.getByIdEventAndPresentation(eventId, presentationId);
    private final LongFunction<ZoneDto> functionGetZone = zoneId -> zoneService.getById(zoneId);
    private final UnaryOperator<Set<ZoneConfigEventDto>> functionValidate = item -> validateConfig(item);
    private final Predicate<String> isGenerateCode = codeConfirmation -> qRGeneratorService.generateQRCodeImage(codeConfirmation);
    private Function<Optional<TicketDto>, ConfirmBuyTicket> functionCreateObjConfirm = ticket -> {
        log.info("PURCHASED-TICKET|{}|{}|{}", ticket.isPresent(), (ticket.isPresent() ? ticket.get().getNumberTicket() : -1L), ticket.isPresent() ? ticket.get().getEventId() : -1L);
        return ConfirmBuyTicket.builder()
                .confirmNumberTicket(ticket.get().getConfirmationNumber())
                .numberTicket(ticket.get().getNumberTicket())
                .build();
    };
    private final UnaryOperator<TicketDto> funcGetTicket = ticket ->
            ticketClient.getByIdNumber(ticket.getEventId(), ticket.getZoneId(), ticket.getCategoryId(), ticket.getPresentationId(), ticket.getNumberTicket());
    private final Predicate<TicketDto> isTicketAvailable = ticket -> (Objects.nonNull(ticket) && StatusTicket.CREATED.equals(ticket.getState()));

    @Autowired
    public TicketServiceImpl(ZoneConfigEventService zoneConfigEventService, ZoneService zoneService
            , TicketClient ticketClient, UserLoggerService userLoggerService, QRGeneratorService qRGeneratorService
            , GenerateTemplatesEmailServiceImpl generateTemplatesEmailService, EmailService emailService) {
        this.zoneConfigEventService = zoneConfigEventService;
        this.zoneService = zoneService;
        this.ticketClient = ticketClient;
        this.userLoggerService = userLoggerService;
        this.qRGeneratorService = qRGeneratorService;
        this.generateTemplatesEmailService = generateTemplatesEmailService;
        this.emailService = emailService;
    }
    @Override
    public Boolean generateTicket(Long idEvent, Long idPresentation) {
        Set<ZoneConfigEventDto> configs = functionGenerateTickets.andThen(this::validateSet).apply(idEvent, idPresentation);
        Function<Set<ZoneConfigEventDto>, Boolean> ticketsFunction = functionValidate
                .andThen(item -> generateTicketObj(item, idEvent, idPresentation))
                .andThen(this::generateTicketIterate)
                .andThen(item -> this.confirmCreate(item, configs));
        return ticketsFunction.apply(configs);
    }

    public Set<ZoneConfigEventDto> validateSet(Set<ZoneConfigEventDto> zoneConfig) {
        return zoneConfig.stream()
                .map(item -> {
                    if (Objects.isNull(item.getCreateTickets())) {
                        item.setCreateTickets(Boolean.FALSE);
                    }
                    return item;
                })
                .filter(Predicate.not(ZoneConfigEventDto::getCreateTickets))
                .collect(Collectors.toSet());
    }

    private Boolean confirmCreate(boolean valida, Set<ZoneConfigEventDto> configs) {
        if (!valida) {
            return false;
        }
        configs.stream().
                forEach(item -> {
                    item.setCreateTickets(Boolean.TRUE);
                    this.zoneConfigEventService.save(item);
                });
        return Boolean.TRUE;
    }

    @Override
    public GenericResponse<ConfirmBuyTicket> buyTicket(BuyTicket buyTicket) {
        if (!isTicketsAvailability.test(buyTicket)) {
            return GenericResponse.<ConfirmBuyTicket>builder()
                    .code(1L)
                    .type(TYPE_EXCEPTION.ERROR)
                    .message("Tickets no disponibles")
                    .build();
        }
        Set<ConfirmBuyTicket> confirmations = createTicket(buyTicket).stream()
                .peek(ticketDto -> log.debug("Ticket pre buy {}", ticketDto))
                .map(this::buyTicket)
                .peek(ticketDto -> log.debug("Ticket buy {}", ticketDto))
                .filter(Optional::isPresent)
                .map(this::generateQR)
                .map(functionCreateObjConfirm)
                .map(this::sendEmailConfirmation)
                .collect(Collectors.toSet());
        return GenericResponse.<ConfirmBuyTicket>builder()
                .code(0L)
                .type(TYPE_EXCEPTION.SUCCESS)
                .message("Tickets Comprados")
                .data(confirmations)
                .build();
    }

    private ConfirmBuyTicket sendEmailConfirmation(ConfirmBuyTicket confirmBuyTicket){
        String htmlTemplate = Optional.ofNullable(generateTemplatesEmailService.buyTicket(confirmBuyTicket.getConfirmNumberTicket()))
                .orElseThrow(() -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al generar el template html para el correo"));
        Boolean isSending = Optional.ofNullable(emailService.sendHtmlMessage(userLoggerService.getUserLogger(), "Compra Ticket", htmlTemplate))
                .orElseThrow(() -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al enviar notificacion de confirmacion de compra"));
        isSendingNotification(confirmBuyTicket, isSending);
        return confirmBuyTicket;
    }
    private void isSendingNotification(ConfirmBuyTicket confirmBuyTicket, boolean sending){
        //TODO persistir en la base de datos que se envio la notificación correctamente
    }
    private Optional<TicketDto> generateQR(Optional<TicketDto> ticket) {
        if (isGenerateCode.test(ticket.orElseThrow( () -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "")).getConfirmationNumber()) ){
            return ticket;
        }
        return Optional.empty();
    }

    @Override
    public GenericResponse<TicketViewDto> getTiketsByUser() {
        Function<String, GenericResponse<TicketViewDto>> ticketsFunction = getUserAuth
                .andThen(this::generateObjectQuery)
                .andThen(ticketClient::search)
                .andThen(this::mapperTicket);
        return ticketsFunction.apply("t");
    }

    public SearchRequest generateObjectQuery(String user) {
        return SearchRequest
                .builder()
                .filters(List.of(
                        FilterRequest.builder()
                                .key("userEmail")
                                .operator(Operator.EQUAL)
                                .fieldType(FieldType.STRING)
                                .value(user)
                                .build()
                ))
                .build();
    }

    public GenericResponse<TicketViewDto> mapperTicket(GenericQuery<TicketViewDto> tickets) {
        return GenericResponse.<TicketViewDto>builder()
                .data(tickets.getResults())
                .code(1L)
                .type(TYPE_EXCEPTION.SUCCESS)
                .message("Consulta ejecutada exitosamente")
                .build();
    }
    private final Function<TicketDto, ResponseEntity<TicketDto>> functBuyTicket = ticket -> ticketClient.buyTicket(StatusTicket.RESERVED
            , ticket.getEventId()
            , ticket.getZoneId()
            , ticket.getCategoryId()
            , ticket.getPresentationId()
            , ticket.getNumberTicket());
    private Optional<TicketDto> buyTicket(TicketDto buyTicket) {
        return (Optional<TicketDto>) functBuyTicket
                .andThen( response -> HttpStatus.NO_CONTENT.equals(response.getStatusCode()) ? Optional.empty() : Optional.of(response.getBody()))
                .apply(buyTicket);
    }
    public Set<TicketDto> createTicket(BuyTicket buyTicket) {
        return buyTicket.getNumberTickets().stream()
                .map(ticketNum -> TicketDto.builder()
                        .eventId(buyTicket.getEventId())
                        .zoneId(buyTicket.getZoneId())
                        .categoryId(buyTicket.getCategoryId())
                        .presentationId(buyTicket.getPresentationId())
                        .numberTicket(ticketNum)
                        .build())
                .collect(Collectors.toSet());
    }
    private final Predicate<BuyTicket> isTicketsAvailability = buyTicket -> createTicket(buyTicket).stream()
            .peek(ticket -> log.debug("ticket pre validated {}", ticket))
            .filter(Predicate.not(this::validateTicket))
            .peek(ticket -> log.debug("ticket validated {}", ticket))
            .count() == 0;

    private boolean validateTicket(TicketDto ticketDto) {
        return isTicketAvailable.test(funcGetTicket.apply(ticketDto));
    }

    private Set<ZoneConfigEventDto> validateConfig(Set<ZoneConfigEventDto> responseSet) {
        if (responseSet.isEmpty()) {
            throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "No existe ninguna zona por generar tickets o las actuales ya tienen tickets generados");
        }
        return responseSet;
    }

    private Set<TicketDto> generateTicketObj(Set<ZoneConfigEventDto> zoneConfigEvents, Long idEvent, Long idPresentation) {
        return zoneConfigEvents.stream()
                .map(zoneConfigEventDto -> generateTickets(zoneConfigEventDto, idEvent, idPresentation))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    private Set<TicketDto> generateTickets(ZoneConfigEventDto zoneConfigEventDto, Long eventId, Long presentationId) {
        Set<TicketDto> ticketsDto = new HashSet<>();
        ZoneDto zone = functionGetZone.apply(zoneConfigEventDto.getZoneId());
        for (long i = 1; i <= zoneConfigEventDto.getNumberOfTickets(); i++) {
            ticketsDto.add(TicketDto.builder()
                    .eventId(eventId)
                    .zoneId(zone.getId())
                    .categoryId(zone.getCategoryId())
                    .presentationId(presentationId)
                    .numberTicket(i)
                    .state(StatusTicket.CREATED)
                    .build());
        }
        return ticketsDto;
    }

    private Boolean generateTicketIterate(Set<TicketDto> tickets) {
        tickets.stream()
                .forEach(this::saveTicket);
        return Boolean.TRUE;
    }

    private void saveTicket(TicketDto ticketDto) {
        ticketClient.save(ticketDto);
    }
}
