package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.ConfigEventEntity;

import java.util.Optional;

public interface ConfigEventService {

    Optional<ConfigEventEntity> findByEventIdAndPresentationId(Long idEvent, Long idPresentation);
}
