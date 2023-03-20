package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "ZoneConfigEventClient", url = "${endpoint.ms-data-access.protocol}:${endpoint.ms-data-access.host}//:${endpoint.ms-data-access.port}/api-acceso-datos/v.1/zone_config_event/")
public interface ZoneConfigEventClient {
    @GetMapping(value = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<ZoneConfigEventDto> getByIdEvent(@PathVariable(value = "id")Long idEvent);
    @GetMapping(value = "/event/{event_id}/presentation/{presentation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<ZoneConfigEventDto> getByIdEventAndPresentation(@PathVariable(value = "event_id")Long idEvent, @PathVariable(value = "presentation_id")Long idPresentation );
}