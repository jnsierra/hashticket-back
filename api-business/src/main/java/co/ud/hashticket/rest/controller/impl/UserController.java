package co.ud.hashticket.rest.controller.impl;

import co.ud.hashticket.rest.services.UserService;
import co.ud.ud.hashticket.dto.UsuarioDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v.1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PatchMapping("/changePassword")
    @Secured("ROLE_UNVERIFIED_USER")
    public ResponseEntity<Boolean> changePassword(@RequestBody UsuarioDto user) {
        return ResponseEntity.ok(userService.changePassword(user.getPassword()));
    }
}