package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.EventClient;
import co.ud.hashticket.rest.services.EventService;
import co.ud.ud.hashticket.dto.EventDto;
import co.ud.ud.hashticket.enumeration.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventClient eventClient;
    @Autowired
    public EventServiceImpl(EventClient eventClient) {
        this.eventClient = eventClient;
    }
    @Override
    public Boolean changeStatusEventWaiting(Long idEvent) {
        return eventClient.updateStatusEvent(idEvent, EventStatus.WAITING);
    }
    @Override
    public Optional<EventDto> getEventById(Long id) {
        return Optional.ofNullable(eventClient.get(id));
    }
}