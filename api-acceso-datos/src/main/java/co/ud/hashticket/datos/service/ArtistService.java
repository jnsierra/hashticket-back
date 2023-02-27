package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.ArtistEntity;

import java.util.Optional;

public interface ArtistService {
    ArtistEntity save(ArtistEntity entity);
    Optional<ArtistEntity> getById(Long id);
}
