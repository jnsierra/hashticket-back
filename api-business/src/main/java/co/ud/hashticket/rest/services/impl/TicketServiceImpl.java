package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.TicketClient;
import co.ud.hashticket.rest.services.TicketService;
import co.ud.hashticket.rest.services.ZoneConfigEventService;
import co.ud.hashticket.rest.services.ZoneService;
import co.ud.ud.hashticket.dto.TicketDto;
import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import co.ud.ud.hashticket.dto.ZoneDto;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {
    private final ZoneConfigEventService zoneConfigEventService;
    private final ZoneService zoneService;
    private final TicketClient ticketClient;
    @Autowired
    public TicketServiceImpl(ZoneConfigEventService zoneConfigEventService, ZoneService zoneService, TicketClient ticketClient) {
        this.zoneConfigEventService = zoneConfigEventService;
        this.zoneService = zoneService;
        this.ticketClient = ticketClient;
    }

    @Override
    public Boolean generateTicket(Long idEvent, Long idPresentation) {
        return findInformation(idEvent, idPresentation);
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
