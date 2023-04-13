package co.ud.hashticket.rest.controller.impl;

import co.ud.hashticket.rest.services.TicketService;
import co.ud.ud.hashticket.dto.responses.GenericResponse;
import co.ud.ud.hashticket.dto.ticket.BuyTicket;
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
        Boolean resp = ticketService.generateTicket(idEvent, idPresentation);
        if(!resp){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GenericResponse.builder()
                        .code(1L)
                        .message("Boletas Generadas Correctamente")
                        .type("SUCCESS")
                .build());
    }
    @PostMapping("/ticket")
    public ResponseEntity<GenericResponse> buyTickets(@RequestBody BuyTicket buyTicket){
        System.out.println(buyTicket.toString());

        return ResponseEntity.noContent().build();
    }

}