package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.EventImagesEntity;

import java.util.Optional;
import java.util.Set;

public interface EventImagesService {
    EventImagesEntity save(EventImagesEntity eventImage);
    Set<EventImagesEntity> findAll();
    Optional<EventImagesEntity> findById(Long id);
    Set<EventImagesEntity> findByEvent(Long idEvent);
}
