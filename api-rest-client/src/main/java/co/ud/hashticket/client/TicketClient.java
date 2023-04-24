package co.ud.hashticket.client;

import co.ud.hashticket.dto.SearchRequest;
import co.ud.ud.hashticket.dto.TicketDto;
import co.ud.ud.hashticket.dto.TicketViewDto;
import co.ud.ud.hashticket.dto.responses.GenericQuery;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ticketClient", url = "${endpoint.ms-data-access.protocol}://${endpoint.ms-data-access.host}:${endpoint.ms-data-access.port}/api-acceso-datos/v.1/ticket")
public interface TicketClient {
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    TicketDto save(@RequestBody TicketDto ticketDto);
    @GetMapping(value = "/event/{event_id}/zone/{zone_id}/category/{category_id}/presentation/{presentation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TicketDto> getById(@PathVariable("event_id")Long eventId
            , @PathVariable("zone_id")Long zoneId
            , @PathVariable("category_id")Long categoryId
            , @PathVariable("presentation_id")Long presentationId);
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    TicketDto getByIdNumber(@RequestParam Long eventId
            ,@RequestParam Long zoneId
            ,@RequestParam Long categoryId
            ,@RequestParam Long presentationId
            ,@RequestParam Long numberTicket);
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TicketDto> buyTicket(@RequestParam StatusTicket state
            , @RequestParam Long eventId
            , @RequestParam Long zoneId
            , @RequestParam Long categoryId
            , @RequestParam Long presentationId
            , @RequestParam Long numberTicket
    );
    @PostMapping(value = "/search")
    GenericQuery<TicketViewDto> search(@RequestBody SearchRequest request);
}
