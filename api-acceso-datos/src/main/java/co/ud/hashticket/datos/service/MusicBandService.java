package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.MusicBandEntity;

import java.util.Optional;
import java.util.Set;

public interface MusicBandService {
    MusicBandEntity save(MusicBandEntity musicBand);
    Optional<MusicBandEntity> getById(Long id);

    Set<MusicBandEntity> getAll();
}
