package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;
import co.ud.ud.hashticket.dto.TicketViewDto;

import java.util.Optional;
import java.util.Set;

public interface TicketService {
    TicketEntity save(TicketEntity ticket);
    Optional<TicketEntity> getById(TicketPkEntity id);
    Set<TicketViewDto> getByEventIdAndPresentationId(Long eventId, Long presentationId, Integer records, Integer page);
    Integer countByEventIdAndPresentationId(Long eventId, Long presentationId);
}