package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.TicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ticketClient", url = "${endpoint.ms-data-access.protocol}:${endpoint.ms-data-access.host}//:${endpoint.ms-data-access.port}/api-acceso-datos/v.1/ticket")
public interface TicketClient {
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    TicketDto save(@RequestBody TicketDto ticketDto);
}
