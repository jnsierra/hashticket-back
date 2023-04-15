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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final ZoneConfigEventService zoneConfigEventService;
    private final ZoneService zoneService;
    private final TicketClient ticketClient;
    private final UserLoggerService userLoggerService;

    @Autowired
    public TicketServiceImpl(ZoneConfigEventService zoneConfigEventService, ZoneService zoneService, TicketClient ticketClient,UserLoggerService userLoggerService) {
        this.zoneConfigEventService = zoneConfigEventService;
        this.zoneService = zoneService;
        this.ticketClient = ticketClient;
        this.userLoggerService = userLoggerService;
    }

    @Override
    public Boolean generateTicket(Long idEvent, Long idPresentation) {
        return findInformation(idEvent, idPresentation);
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

    private Boolean findInformation(Long idEvent, Long idPresentation){
        Set<ZoneConfigEventDto> responseSet = zoneConfigEventService.getByIdEventAndPresentation(idEvent, idPresentation);
        responseSet
                .stream()
                .forEach(zoneConfigEvent -> {
                    ZoneDto item = zoneService.getById(zoneConfigEvent.getZoneId());
                    generateTickets(idEvent, item.getId(), item.getCategoryId(), idPresentation, zoneConfigEvent.getNumberOfTickets());
                });
        return Boolean.TRUE;
    }

    private void generateTickets(Long eventId, Long zoneId, Long categoryId, Long presentationId, Long numberOfTickets){
        for(long i=1 ; i <= numberOfTickets; i++){
            ticketClient.save(TicketDto.builder()
                            .eventId(eventId)
                            .zoneId(zoneId)
                            .categoryId(categoryId)
                            .presentationId(presentationId)
                            .numberTicket(i)
                            .state(StatusTicket.CREATED)
                    .build());
        }
    }

}
