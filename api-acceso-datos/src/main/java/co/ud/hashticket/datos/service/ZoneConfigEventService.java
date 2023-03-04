package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;

import java.util.Optional;

public interface ZoneConfigEventService {
    ZoneConfigEventEntity save(ZoneConfigEventEntity zoneConfigEvent);
    Optional<ZoneConfigEventEntity> getById(Long id);
}