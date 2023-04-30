package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends CrudRepository<UserTypeEntity,Long>, JpaRepository<UserTypeEntity, Long> {
    UserTypeEntity findByType(String type);
}