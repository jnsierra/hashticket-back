package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.PresentationEntity;

import java.util.Optional;
import java.util.Set;

public interface PresentationService {

    PresentationEntity save(PresentationEntity presentation);
    Optional<PresentationEntity> getById(Long id);
    Set<PresentationEntity> getAll();
    Set<PresentationEntity> findByEvent(Long eventId);
}
