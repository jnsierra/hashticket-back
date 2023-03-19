package co.ud.hashticket.pub.controller;

import co.ud.hashticket.pub.service.TokenService;
import co.ud.ud.hashticket.dto.TokenDto;
import co.ud.ud.hashticket.dto.UsuarioDto;
import co.ud.ud.hashticket.enumeration.LOGIN_ACTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final TokenService tokenService;
    @Autowired
    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UsuarioDto usuarioDto) {
        //Genero el token en la aplicacion
        Optional<TokenDto> token = tokenService.generateTokenUser(usuarioDto.getCorreo());
        if (token.isPresent()) {
            token.get().setLoginAction(LOGIN_ACTION.SUCCESS);
            return ResponseEntity.ok(token.get());
        }
        return ResponseEntity.noContent().build();
    }
}
