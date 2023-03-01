package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.ZoneEntity;

import java.util.Optional;

public interface ZoneService {
    ZoneEntity save(ZoneEntity zone);
    Optional<ZoneEntity> getById(Long id);
}
