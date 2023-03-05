package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;
import co.ud.hashticket.datos.repository.TicketRepository;
import co.ud.hashticket.datos.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
}
