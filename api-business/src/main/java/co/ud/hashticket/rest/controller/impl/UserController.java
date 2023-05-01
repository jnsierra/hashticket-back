package co.ud.hashticket.rest.controller.impl;

import co.ud.ud.hashticket.dto.UsuarioDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v.1/user")
public class UserController {

    @PatchMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody UsuarioDto usuario) {
        log.info("Llego el usuario {}", usuario);
        return ResponseEntity.ok(true);
    }
}
