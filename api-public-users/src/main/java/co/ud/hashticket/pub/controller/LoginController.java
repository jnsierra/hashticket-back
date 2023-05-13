package co.ud.hashticket.pub.controller;

import co.ud.hashticket.pub.service.LoginService;
import co.ud.hashticket.pub.service.TokenService;
import co.ud.ud.hashticket.dto.TokenDto;
import co.ud.ud.hashticket.dto.UsuarioDto;
import co.ud.ud.hashticket.enumeration.LOGIN_ACTION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    private final TokenService tokenService;
    private final LoginService loginService;
    @Autowired
    public LoginController(TokenService tokenService, LoginService loginService) {
        this.tokenService = tokenService;
        this.loginService = loginService;
    }
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UsuarioDto usuarioDto) {
        LOGIN_ACTION login = loginService.validaLogin(usuarioDto.getEmail(), usuarioDto.getPassword());
        if (LOGIN_ACTION.SUCCESS.equals(login) || LOGIN_ACTION.SUCCESS_CHANGE_PASSWORD.equals(login)) {
            Optional<TokenDto> token = tokenService.generateTokenUser(usuarioDto.getEmail());
            if (token.isPresent()) {
                log.info("LOGIN|200|{}|{}",usuarioDto.getEmail(), login);
                token.get().setLoginAction(login);
                return ResponseEntity.ok(token.get());
            }
        }
        log.info("LOGIN|401|{}|{}",usuarioDto.getEmail(), login.toString());
        return new ResponseEntity<>(TokenDto.builder()
                .loginAction(LOGIN_ACTION.UN_AUTHORIZED)
                .mensaje("Usuario no autorizado")
                .build(), HttpStatus.UNAUTHORIZED );
    }
}
