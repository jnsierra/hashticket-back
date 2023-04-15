package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.repository.TicketRepository;
import co.ud.hashticket.pub.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public Set<TicketEntity> getByEventAndPresentationAndZoneAndCategory(Long eventId, Long presentationId, Long zoneId, Long categoryId) {
        return ticketRepository.getByEventAndPresentationAndZoneAndCategory(eventId,presentationId,zoneId,categoryId);
    }
}
