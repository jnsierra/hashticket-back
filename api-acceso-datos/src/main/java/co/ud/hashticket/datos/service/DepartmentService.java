package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.DepartmentEntity;

import java.util.Optional;
import java.util.Set;

public interface DepartmentService {
    Set<DepartmentEntity> getSetDepartmentByCountry(Long id);

    Optional<DepartmentEntity> getById(Long id);
}
