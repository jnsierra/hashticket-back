package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ZoneConfigEventRepository extends JpaRepository<ZoneConfigEventEntity, Long>, CrudRepository<ZoneConfigEventEntity, Long> {
    Set<ZoneConfigEventEntity> getZoneConfigByEvent(Long idEvent);
    Set<ZoneConfigEventEntity> getZoneConfigByEventAndPresentation(Long idEvent, Long idPresentation);
}