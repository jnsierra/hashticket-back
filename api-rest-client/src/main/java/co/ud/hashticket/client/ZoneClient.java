package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.ZoneDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zoneClient", url = "http://localhost:5003/api-acceso-datos/v.1/zone")
public interface ZoneClient {
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ZoneDto getById(@PathVariable(value = "id")Long id);
}
