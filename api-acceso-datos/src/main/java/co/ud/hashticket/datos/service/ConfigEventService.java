package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.ConfigEventEntity;

import java.util.Optional;

public interface ConfigEventService {
    ConfigEventEntity save(ConfigEventEntity entity);
    Optional<ConfigEventEntity> findById(Long id);
}
