package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.hashticket.datos.entity.TicketPkEntity;
import co.ud.hashticket.datos.filtering.SearchRequest;
import co.ud.hashticket.datos.mapper.TicketMapper;
import co.ud.hashticket.datos.mapper.TicketPkMapper;
import co.ud.hashticket.datos.service.TicketService;
import co.ud.ud.hashticket.dto.TicketDto;
import co.ud.ud.hashticket.dto.TicketViewDto;
import co.ud.ud.hashticket.dto.responses.GenericQuery;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/ticket")
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<TicketDto> save(@RequestBody TicketDto ticketDto){
        TicketEntity entity = TicketMapper.INSTANCE.map(ticketDto);
        return ResponseEntity.ok(TicketMapper.INSTANCE.map( ticketService.save(entity) ));
    }
    @GetMapping(value = "/event/{event_id}/zone/{zone_id}/category/{category_id}/presentation/{presentation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDto> getById(@PathVariable("event_id")Long eventId
            ,@PathVariable("zone_id")Long zoneId
            ,@PathVariable("category_id")Long categoryId
            ,@PathVariable("presentation_id")Long presentationId
    ){
        TicketPkEntity entityPk = TicketPkMapper.INSTANCE.map(eventId, zoneId, categoryId, presentationId);
        Optional<TicketEntity> entity = ticketService.getById(entityPk);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(TicketMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDto> getByIdNumber(@RequestParam Long eventId
            ,@RequestParam Long zoneId
            ,@RequestParam Long categoryId
            ,@RequestParam Long presentationId
            ,@RequestParam Long numberTicket
    ){
        TicketPkEntity entityPk = TicketPkMapper.INSTANCE.map(eventId, zoneId, categoryId, presentationId, numberTicket);
        Optional<TicketEntity> entity = ticketService.getById(entityPk);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(TicketMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping(value = "/event/{event_id}/presentation/{presentation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericQuery<TicketViewDto>> getByEventAndPresentation(@PathVariable("event_id")Long eventId
            , @PathVariable("presentation_id")Long presentationId
            , @RequestParam Integer myRecord
            , @RequestParam Integer page){
        Set<TicketViewDto> entities = ticketService.getByEventIdAndPresentationId(eventId, presentationId, myRecord, page);
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GenericQuery.<TicketViewDto>builder()
                .results(entities)
                .records(myRecord)
                        .page(page)
                .totalRecords(ticketService.countByEventIdAndPresentationId(eventId, presentationId))
                .build());
    }
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDto> buyTicket(@RequestParam StatusTicket state
            , @RequestParam Long eventId
            , @RequestParam Long zoneId
            , @RequestParam Long categoryId
            , @RequestParam Long presentationId
            , @RequestParam Long numberTicket
    ) {
        Optional<TicketEntity> entity = ticketService.buyTicket(state, eventId, zoneId, categoryId, presentationId, numberTicket);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(TicketMapper.INSTANCE.map(entity.get()));
    }

    @GetMapping(value = "/event/{eventId}/presentation/{presentationId}/by/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TicketDto>> getByEmailAndEventAndPresentation(@PathVariable("eventId") Long eventId
            ,@PathVariable("{presentationId}") Long presentationId
            ,@PathVariable("{email}") String email
    ){
        Set<TicketEntity> entities = ticketService.getByEmailAndEventAndPresentation(email, eventId, presentationId);
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(TicketMapper.INSTANCE.map(entities));
    }

    @PostMapping(value = "/search")
    public GenericQuery<TicketViewDto> search(@RequestBody SearchRequest request) {
        return ticketService.searchTickets(request);
    }
}