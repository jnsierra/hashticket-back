package co.ud.hashticket.rest.controller.impl;

import co.ud.hashticket.rest.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/ticket")
public class TicketController {

    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/generate/event/{event_id}")
    public String generate(@PathVariable(value = "event_id") Long id) {
        ticketService.generateTicket(id);
        return "Este es el servicio de eventos";
    }

}