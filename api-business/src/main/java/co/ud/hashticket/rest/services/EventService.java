package co.ud.hashticket.rest.services;

import co.ud.ud.hashticket.dto.EventDto;

import java.util.Optional;

public interface EventService {
    Boolean changeStatusEventWaiting(Long idEvent);
    Optional<EventDto> getEventById(Long id);
}