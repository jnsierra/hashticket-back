package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.EventImagesEntity;
import co.ud.ud.hashticket.enumeration.TypeImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EventImagesRepository extends JpaRepository< EventImagesEntity, Long >, CrudRepository<EventImagesEntity, Long> {
    Set<EventImagesEntity> findByEventAndTypeImages(Long idEvent, TypeImages typeImages);
    Set<EventImagesEntity> findByEvent(Long idEvent);
}