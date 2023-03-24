package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.ArtistEntity;

import java.util.Optional;
import java.util.Set;

public interface ArtistService {
    ArtistEntity save(ArtistEntity entity);
    Optional<ArtistEntity> getById(Long id);
    Set<ArtistEntity> getAll();
}
