package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;

import java.util.Optional;

public interface TicketService {
    TicketEntity save(TicketEntity ticket);
    Optional<TicketEntity> getById(TicketPkEntity id);
}