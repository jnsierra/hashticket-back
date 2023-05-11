package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.ConfigEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "configEventClient", url = "${endpoint.ms-data-access.protocol}://${endpoint.ms-data-access.host}:${endpoint.ms-data-access.port}/api-acceso-datos/v.1/config_event")
public interface ConfigEventClient {
    @GetMapping(value = "/event/{idEvent}/presentation/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConfigEventDto> getByEventAndPresentation(@PathVariable(value = "idEvent") Long idEvent,
                                                     @PathVariable(value = "idPresentation") Long idPresentation);

}
