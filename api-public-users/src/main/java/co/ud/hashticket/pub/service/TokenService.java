package co.ud.hashticket.pub.service;

import co.ud.ud.hashticket.dto.TokenDto;

import java.util.Optional;

public interface TokenService {
    Optional<TokenDto> generateTokenUser(String email);
}
