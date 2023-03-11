package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.EventEntity;

import java.util.Optional;
import java.util.Set;

public interface EventService {
    EventEntity save(EventEntity event);
    Optional<EventEntity> findById(Long id);
    Set<EventEntity> getAll();
}
