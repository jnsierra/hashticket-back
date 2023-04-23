package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;
import co.ud.hashticket.datos.filtering.SearchRequest;
import co.ud.ud.hashticket.dto.TicketViewDto;
import co.ud.ud.hashticket.dto.responses.GenericQuery;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface TicketService {
    TicketEntity save(TicketEntity ticket);
    Optional<TicketEntity> getById(TicketPkEntity id);
    Set<TicketViewDto> getByEventIdAndPresentationId(Long eventId, Long presentationId, Integer records, Integer page);
    Integer countByEventIdAndPresentationId(Long eventId, Long presentationId);
    Integer updateState( StatusTicket state,
                         Long eventId,
                         Long zoneId,
                         Long categoryId,
                         Long presentationId,
                         Long numberTicket,
                         String confirmationNumber,
                         String user);
    Optional<TicketEntity> buyTicket( StatusTicket state,
                         Long eventId,
                         Long zoneId,
                         Long categoryId,
                         Long presentationId,
                         Long numberTicket)throws Exception;
    Set<TicketEntity> getByEmailAndEventAndPresentation(String email, Long eventId, Long presentationId);
    GenericQuery<TicketViewDto> searchTickets(SearchRequest request);
}