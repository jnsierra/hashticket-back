package co.ud.hashticket.rest.services;

import co.ud.ud.hashticket.dto.responses.GenericResponse;
import co.ud.ud.hashticket.dto.ticket.BuyTicket;
import co.ud.ud.hashticket.dto.ticket.ConfirmBuyTicket;

public interface TicketService {
    Boolean generateTicket(Long idEvent, Long presentation);
    GenericResponse< ConfirmBuyTicket >buyTicket(BuyTicket buyTicket);
}
