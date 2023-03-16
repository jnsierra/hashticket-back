package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.EventImagesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eventImagesClient", url = "http://localhost:5005/api-acceso-datos/v.1/event_images")
public interface EventImagesClient {
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EventImagesDto save(@RequestBody EventImagesDto eventImagesDto);
}
