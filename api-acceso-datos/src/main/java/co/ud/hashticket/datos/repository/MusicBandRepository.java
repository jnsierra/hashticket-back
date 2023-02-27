package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.MusicBandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicBandRepository extends JpaRepository<MusicBandEntity, Long>, CrudRepository<MusicBandEntity, Long> {
}