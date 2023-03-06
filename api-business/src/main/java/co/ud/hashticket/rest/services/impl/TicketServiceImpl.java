package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.EventClient;
import co.ud.hashticket.rest.services.TicketService;
import co.ud.ud.hashticket.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    private final EventClient eventClient;
    @Autowired
    public TicketServiceImpl(EventClient eventClient) {
        this.eventClient = eventClient;
    }
    @Override
    public Boolean generateTicket(Long idEvent) {
        EventDto response = eventClient.get(idEvent);
        System.out.println(response);
        return Boolean.TRUE;
    }
}
