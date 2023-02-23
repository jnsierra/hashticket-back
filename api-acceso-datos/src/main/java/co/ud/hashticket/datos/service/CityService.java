package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.CityEntity;

import java.util.Optional;
import java.util.Set;

public interface CityService {
    Optional<CityEntity> getById(Long cityCode,Long departmentCode);
    Set<CityEntity> getCitiesByDepartment(Long departmentCode);
}
