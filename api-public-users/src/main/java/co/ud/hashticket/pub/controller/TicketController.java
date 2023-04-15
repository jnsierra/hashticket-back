package co.ud.hashticket.pub.controller;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.pub.mapper.TicketMapper;
import co.ud.hashticket.pub.service.TicketService;
import co.ud.ud.hashticket.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v.1/ticket")
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping(value = "/event/{idEvent}/presentation/{idPresentation}/zone/{idZone}/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TicketDto>> getByEvent(@PathVariable(value = "idEvent")Long idEvent, @PathVariable(value = "idPresentation")Long idPresentation
            , @PathVariable(value = "idZone")Long idZone, @PathVariable(value = "idCategory")Long idCategory){
        Set<TicketEntity> entities = ticketService.getByEventAndPresentationAndZoneAndCategory(idEvent, idPresentation,idZone, idCategory);
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(TicketMapper.INSTANCE.map(entities));
    }
}