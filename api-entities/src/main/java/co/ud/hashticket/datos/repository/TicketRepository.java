package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, TicketPkEntity>, CrudRepository<TicketEntity, TicketPkEntity> {

    List<TicketEntity> getByEventIdAndPresentationId(Long eventId, Long presentationId, Pageable pageable);

    Integer countByEventIdAndPresentationId(Long eventId, Long presentationId);
}
