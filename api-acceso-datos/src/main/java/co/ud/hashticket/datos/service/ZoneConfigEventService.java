package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;

import java.util.Optional;
import java.util.Set;

public interface ZoneConfigEventService {
    ZoneConfigEventEntity save(ZoneConfigEventEntity zoneConfigEvent);
    Optional<ZoneConfigEventEntity> getById(Long id);
    Set<ZoneConfigEventEntity> getZoneConfigByEvent(Long id);
    Set<ZoneConfigEventEntity> getZoneConfigByEventAndPresentation(Long idEvent, Long idPresentation);
}