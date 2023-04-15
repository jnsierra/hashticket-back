package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneEntity, Long>, CrudRepository<ZoneEntity, Long> {
    Set<ZoneEntity> findByCategory(Long categoryId);
}
