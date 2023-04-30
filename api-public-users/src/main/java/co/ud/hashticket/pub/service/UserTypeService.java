package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.UserTypeEntity;

import java.util.Optional;

public interface UserTypeService {
    Optional<UserTypeEntity> findByType(String type);
}
