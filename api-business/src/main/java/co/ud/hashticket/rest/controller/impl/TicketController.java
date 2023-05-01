package co.ud.hashticket.rest.controller.impl;

import co.ud.hashticket.rest.services.TicketService;
import co.ud.ud.hashticket.dto.responses.GenericResponse;
import co.ud.ud.hashticket.dto.ticket.BuyTicket;
import co.ud.ud.hashticket.dto.ticket.ConfirmBuyTicket;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v.1/ticket")
public class TicketController {

    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/generate/event/{event_id}/presentation/{presentation_id}")
    public ResponseEntity<GenericResponse> generate(@PathVariable(value = "event_id") Long idEvent, @PathVariable(value = "presentation_id") Long idPresentation ) {
        boolean resp = ticketService.generateTicket(idEvent, idPresentation);
        if(!resp){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GenericResponse.builder()
                        .code(1L)
                        .message("Boletas Generadas Correctamente")
                        .type(TYPE_EXCEPTION.SUCCESS)
                .build());
    }
    @PostMapping("/buy")
    public ResponseEntity<GenericResponse<ConfirmBuyTicket>> buyTickets(@RequestBody BuyTicket buyTicket){
        return ResponseEntity.ok(ticketService.buyTicket(buyTicket));
    }

    @GetMapping("/by/user")
    public ResponseEntity<GenericResponse> getTickets() {
        return ResponseEntity.ok(ticketService.getTiketsByUser());
    }
}