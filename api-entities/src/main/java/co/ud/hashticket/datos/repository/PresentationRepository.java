package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, Long>, CrudRepository<PresentationEntity, Long> {
    Set<PresentationEntity> findByEvent(Long idEvent);
}
