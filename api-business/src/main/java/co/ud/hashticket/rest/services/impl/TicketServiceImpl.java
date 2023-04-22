package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.TicketClient;
import co.ud.hashticket.rest.services.TicketService;
import co.ud.hashticket.rest.services.ZoneConfigEventService;
import co.ud.hashticket.rest.services.ZoneService;
import co.ud.hashticket.security.service.UserLoggerService;
import co.ud.ud.hashticket.dto.TicketDto;
import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import co.ud.ud.hashticket.dto.ZoneDto;
import co.ud.ud.hashticket.dto.responses.GenericResponse;
import co.ud.ud.hashticket.dto.ticket.BuyTicket;
import co.ud.ud.hashticket.dto.ticket.ConfirmBuyTicket;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import co.ud.ud.hashticket.exception.BusinessException;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private ZoneConfigEventService zoneConfigEventService;
    private ZoneService zoneService;
    private final TicketClient ticketClient;
    private final UserLoggerService userLoggerService;

    private final BiFunction<Long, Long, Set<ZoneConfigEventDto>> functionGenerateTickets = (eventId, presentationId) -> zoneConfigEventService.getByIdEventAndPresentation(eventId, presentationId)
            .stream()
            .filter(Predicate.not(ZoneConfigEventDto::getCreateTickets))
            .collect(Collectors.toSet());
    private final Function<Long, ZoneDto> functionGetZone = zoneId -> zoneService.getById(zoneId);
    private final Function<Set<ZoneConfigEventDto>,Set<ZoneConfigEventDto>> functionValidate = item -> validateConfig(item);

    @Autowired
    public TicketServiceImpl(ZoneConfigEventService zoneConfigEventService, ZoneService zoneService, TicketClient ticketClient,UserLoggerService userLoggerService) {
        this.zoneConfigEventService = zoneConfigEventService;
        this.zoneService = zoneService;
        this.ticketClient = ticketClient;
        this.userLoggerService = userLoggerService;
    }

    @Override
    public Boolean generateTicket(Long idEvent, Long idPresentation) {
        Set<ZoneConfigEventDto> configs = functionGenerateTickets.apply(idEvent, idPresentation);
        Function<Set<ZoneConfigEventDto>, Boolean> ticketsFunction = functionValidate
                .andThen(item -> generateTicketObj(item, idEvent, idPresentation))
                .andThen(this::generateTicketIterate)
                .andThen(item -> this.confirmCreate(item, configs));
        Boolean valida = ticketsFunction.apply(configs);
        return valida;
    }
    private Boolean confirmCreate(Boolean valida, Set<ZoneConfigEventDto> configs){
        configs.stream().
                forEach(item -> {
                    item.setCreateTickets(Boolean.TRUE);
                    this.zoneConfigEventService.save(item);
                });
        return Boolean.TRUE;
    }


    @Override
    public GenericResponse< ConfirmBuyTicket >buyTicket(BuyTicket buyTicket) {
        System.out.println("**************");
        System.out.println(userLoggerService.getUserLogger());
        System.out.println("**************");

        if(!validateAvailability(buyTicket)){
            return GenericResponse.<ConfirmBuyTicket>builder()
                    .code(1L)
                    .type("error")
                    .message("Tickets no disponibles")
                    .build();
        }
        Set<ConfirmBuyTicket> confirmations = buyTicket.getNumberTickets().stream()
                .map(item -> this.buyTicket(buyTicket, item))
                .peek(System.out::println)
                .map(item -> ConfirmBuyTicket.builder()
                        .confirmNumberTicket(item.get().getConfirmationNumber())
                        .numberTicket(item.get().getNumberTicket())
                        .build())
                .peek(System.out::println)
                .collect(Collectors.toSet());
        return GenericResponse.<ConfirmBuyTicket>builder()
                .code(0L)
                .type("ok")
                .message("Tickets Comprados")
                .data(confirmations)
                .build();
    }
    private Optional<TicketDto> buyTicket(BuyTicket buyTicket, Long numberTicket){
        ResponseEntity<TicketDto> response = ticketClient.buyTicket(StatusTicket.RESERVED, buyTicket.getEventId()
                , buyTicket.getZoneId()
                , buyTicket.getCategoryId()
                , buyTicket.getPresentationId()
                , numberTicket);
        if(HttpStatus.NO_CONTENT.equals(response.getStatusCode()) ){
            return Optional.empty();
        }
        return Optional.of(response.getBody());
    }
    public Boolean validateAvailability(BuyTicket buyTicket){
        Set<Long> availability = buyTicket.getNumberTickets().stream()
                .filter(item -> validateTicket(buyTicket.getEventId(), buyTicket.getZoneId(), buyTicket.getCategoryId(), buyTicket.getPresentationId(), item))
                .collect(Collectors.toSet());
        return (availability.size() == buyTicket.getNumberTickets().size()) ? Boolean.TRUE : Boolean.FALSE;
    }
    private Boolean validateTicket(Long eventId
            , Long zoneId
            , Long categoryId
            , Long presentationId
            , Long numberTicket){
        TicketDto ticket = ticketClient.getByIdNumber(eventId, zoneId, categoryId, presentationId, numberTicket);
        if(Objects.nonNull(ticket) && StatusTicket.CREATED.equals(ticket.getState())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    private Set<ZoneConfigEventDto> validateConfig(Set<ZoneConfigEventDto> responseSet){
        if(responseSet.isEmpty()){
            throw new BusinessException(2L,"error","No existe ninguna zona por generar tickets o las actuales ya tienen tickets generados");
        }
        return responseSet;
    }

    private Set<TicketDto> generateTicketObj(Set<ZoneConfigEventDto> zoneConfigEvents, Long idEvent, Long idPresentation){
        return zoneConfigEvents.stream()
                .map(zoneConfigEventDto -> generateTickets(zoneConfigEventDto, idEvent, idPresentation))
                .flatMap(tickets -> tickets.stream())
                .collect(Collectors.toSet());
    }


    private Set<TicketDto> generateTickets(ZoneConfigEventDto zoneConfigEventDto, Long eventId, Long presentationId){
        Set<TicketDto> ticketsDto = new HashSet<>();
        ZoneDto zone = functionGetZone.apply(zoneConfigEventDto.getZoneId());
        for(long i=1; i <= zoneConfigEventDto.getNumberOfTickets(); i++){
            ticketsDto.add(TicketDto.builder()
                    .eventId(eventId)
                    .zoneId(zone.getId())
                    .categoryId(zone.getCategoryId())
                    .presentationId(presentationId)
                    .numberTicket(i)
                    .state(StatusTicket.CREATED)
                    .build());
        }
        return  ticketsDto;
    }
    private Boolean generateTicketIterate(Set<TicketDto> tickets){
        tickets.stream()
                .forEach(this::saveTicket);
        return Boolean.TRUE;
    }
    private void saveTicket(TicketDto ticketDto){
        ticketClient.save(ticketDto);
    }


}
