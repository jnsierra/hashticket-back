package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "ZoneConfigEventClient", url = "localhost:5003/api-acceso-datos/v.1/zone_config_event/")
public interface ZoneConfigEventClient {
    @GetMapping(value = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<ZoneConfigEventDto> getByIdEvent(@PathVariable(value = "id")Long idEvent);
}