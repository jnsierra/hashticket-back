package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.UserTypeEntity;

import java.util.Optional;

public interface UserTypeService {
    Optional<UserTypeEntity> getByType(String type);
}
