package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.ud.hashticket.enumeration.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>, CrudRepository<EventEntity, Long> {
    Set<EventEntity> findByEventStatus(EventStatus eventStatus);
    Optional<EventEntity> findByIdAndPresentation(Long eventId, Long presentationId);
    Set<EventEntity> findByEventStatusAndAfterTodayEvent(EventStatus eventStatus);
    @Modifying
    Integer updateEventStatus(Long id, EventStatus eventStatus);
}
