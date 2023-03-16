package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.EventImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventImagesRepository extends JpaRepository< EventImagesEntity, Long >, CrudRepository<EventImagesEntity, Long> {
}
