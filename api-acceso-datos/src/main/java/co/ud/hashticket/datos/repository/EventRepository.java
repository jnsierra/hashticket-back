package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>, CrudRepository<EventEntity, Long> {
}
