package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.CityEntity;
import co.ud.hashticket.datos.entity.CityPkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, CityPkEntity>, CrudRepository<CityEntity, CityPkEntity> {

    Set<CityEntity> findByDepartment(Long departmentCode);
}
