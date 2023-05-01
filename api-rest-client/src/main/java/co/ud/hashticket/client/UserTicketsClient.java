package co.ud.hashticket.client;

import co.ud.ud.hashticket.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "userClient", url = "${endpoint.ms-data-access.protocol}://${endpoint.ms-data-access.host}:${endpoint.ms-data-access.port}/api-acceso-datos/v.1/user")
public interface UserTicketsClient {
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<UsuarioDto> save(@RequestBody UsuarioDto usuarioDto);
    @PostMapping(value = "/changePassword")
    Boolean updatePasswordUser(@RequestBody UsuarioDto usuarioDto);
}
