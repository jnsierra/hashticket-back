package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>, CrudRepository<DepartmentEntity, Long> {

    Set<DepartmentEntity> findByCountry(Long idCountry);
}
