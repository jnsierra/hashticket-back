package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.PresentationEntity;

import java.util.Optional;

public interface PresentationService {

    PresentationEntity save(PresentationEntity presentation);
    Optional<PresentationEntity> getById(Long id);
}
