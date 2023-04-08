package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;
import co.ud.hashticket.datos.mapper.TicketMapper;
import co.ud.hashticket.datos.repository.TicketRepository;
import co.ud.hashticket.datos.service.TicketService;
import co.ud.ud.hashticket.dto.TicketViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public TicketEntity save(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }
    @Override
    public Optional<TicketEntity> getById(TicketPkEntity id) {
        return ticketRepository.findById(id);
    }
    @Override
    public Set<TicketViewDto> getByEventIdAndPresentationId(Long eventId, Long presentationId, Integer records, Integer page) {
        Pageable pageable = PageRequest.of(page, records);
        List<TicketEntity> result = ticketRepository.getByEventIdAndPresentationId(eventId, presentationId, pageable);
        return result.stream().map(TicketMapper.INSTANCE::mapToView)
                .collect(Collectors.toSet());
    }
    @Override
    public Integer countByEventIdAndPresentationId(Long eventId, Long presentationId) {
        return ticketRepository.countByEventIdAndPresentationId(eventId, presentationId);
    }
}