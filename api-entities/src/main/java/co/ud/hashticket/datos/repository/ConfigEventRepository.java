package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ConfigEventRepository extends JpaRepository<ConfigEventEntity, Long>, CrudRepository<ConfigEventEntity, Long> {
    Optional<ConfigEventEntity> findByEventIdAndPresentation(Long idEvent, Long idPresentation);

    Set<ConfigEventEntity> findByEventId(Long idEvent);
}
