package co.ud.hashticket.pub.controller;

import co.ud.ud.hashticket.dto.ExternalUserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v.1/external_user")
public class ExternalUserController {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExternalUserDto> save(@Valid @RequestBody ExternalUserDto externalUserDto){
        return ResponseEntity.noContent().build();
    }
}
