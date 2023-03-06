package co.ud.hashticket.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ZoneConfigEventClient", url = "localhost:5003/api-acceso-datos/v.1/zone_config_event/")
public interface ZoneConfigEventClient {

}
