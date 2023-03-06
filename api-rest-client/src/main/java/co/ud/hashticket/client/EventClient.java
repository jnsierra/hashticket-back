package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.EventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eventClient", url = "http://localhost:5003/api-acceso-datos/v.1/event")
public interface EventClient {
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    EventDto get(@PathVariable(value = "id")Long id);
}