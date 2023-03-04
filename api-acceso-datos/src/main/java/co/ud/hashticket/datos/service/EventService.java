package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.EventEntity;

import java.util.Optional;

public interface EventService {
    EventEntity save(EventEntity event);
    Optional<EventEntity> findById(Long id);
}
