package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.ZoneEntity;

import java.util.Set;

public interface ZoneService {
    Set<ZoneEntity> getByCategory(Long idCategory);
}