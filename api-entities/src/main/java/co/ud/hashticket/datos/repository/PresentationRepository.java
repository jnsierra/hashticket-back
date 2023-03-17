package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, Long>, CrudRepository<PresentationEntity, Long> {
}
