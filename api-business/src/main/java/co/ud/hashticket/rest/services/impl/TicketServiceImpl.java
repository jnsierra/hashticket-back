package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.EventClient;
import co.ud.hashticket.client.ZoneConfigEventClient;
import co.ud.hashticket.rest.services.TicketService;
import co.ud.ud.hashticket.dto.EventDto;
import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {
    private final EventClient eventClient;
    private final ZoneConfigEventClient zoneConfigEventClient;
    @Autowired
    public TicketServiceImpl(EventClient eventClient, ZoneConfigEventClient zoneConfigEventClient) {
        this.eventClient = eventClient;
        this.zoneConfigEventClient = zoneConfigEventClient;
    }
    @Override
    public Boolean generateTicket(Long idEvent) {
        Set<ZoneConfigEventDto> responseSet = zoneConfigEventClient.getByIdEvent(idEvent);
        System.out.println(responseSet);
        EventDto response = eventClient.get(idEvent);
        System.out.println(response);
        return Boolean.TRUE;
    }
}
