package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.hashticket.datos.mapper.UserMapper;
import co.ud.hashticket.datos.service.UserService;
import co.ud.ud.hashticket.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<UsuarioDto> save(@RequestBody UsuarioDto usuarioDto){
        UserEntity entity = UserMapper.INSTANCE.map(usuarioDto);
        return ResponseEntity.ok(UserMapper.INSTANCE.map( entity ));
    }
}