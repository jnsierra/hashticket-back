package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long>, CrudRepository<ArtistEntity, Long> {
}
