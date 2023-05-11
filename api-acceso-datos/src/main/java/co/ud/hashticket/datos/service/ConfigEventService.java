package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.ConfigEventEntity;

import java.util.Optional;
import java.util.Set;

public interface ConfigEventService {
    ConfigEventEntity save(ConfigEventEntity entity);
    Optional<ConfigEventEntity> findById(Long id);
    Set<ConfigEventEntity> getAll();
    Set<ConfigEventEntity> findByEventId(Long eventId);
    Optional<Long> recordSale(Long eventId, Long presentationId);
    Optional<ConfigEventEntity> findByEventIdAndPresentationId(Long eventId, Long presentationId);
}
