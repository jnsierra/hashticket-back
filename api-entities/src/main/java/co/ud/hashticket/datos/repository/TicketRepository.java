package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, TicketPkEntity>, CrudRepository<TicketEntity, TicketPkEntity> {

    List<TicketEntity> getByEventIdAndPresentationId(Long eventId, Long presentationId, Pageable pageable);

    Integer countByEventIdAndPresentationId(Long eventId, Long presentationId);
    @Modifying
    Integer updateState(@Param(value = "state") StatusTicket state,
                        @Param(value = "eventId") Long eventId,
                        @Param(value = "zoneId") Long zoneId,
                        @Param(value = "categoryId") Long categoryId,
                        @Param(value = "presentationId") Long presentationId,
                        @Param(value = "numberTicket")Long numberTicket,
                        @Param(value = "confirmationNumber")String confirmationNumber,
                        @Param(value = "user")String user);
}
