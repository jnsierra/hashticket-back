package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.EventEntity;

import java.util.Optional;
import java.util.Set;

public interface EventService {
    Set<EventEntity> getAllActive();
    Optional<EventEntity> getById(Long id);

    Optional<EventEntity> getByIdAndPresentationId(Long id, Long idPresentation);
}
