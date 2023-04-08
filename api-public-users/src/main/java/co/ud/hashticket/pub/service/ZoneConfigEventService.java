package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;

import java.util.Set;

public interface ZoneConfigEventService {
    Set<ZoneConfigEventEntity> getByIdEventAndPresentId(Long idEvent, Long idPresent);
}
