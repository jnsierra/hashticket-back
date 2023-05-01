package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.UserTicketsClient;
import co.ud.hashticket.rest.services.UserService;
import co.ud.hashticket.security.service.UserLoggerService;
import co.ud.ud.hashticket.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserTicketsClient userTicketsClient;
    private final UserLoggerService userLoggerService;
    @Autowired
    public UserServiceImpl(UserTicketsClient userTicketsClient, UserLoggerService userLoggerService) {
        this.userTicketsClient = userTicketsClient;
        this.userLoggerService = userLoggerService;
    }
    @Override
    public boolean changePassword(String password) {
        return userTicketsClient.updatePasswordUser(UsuarioDto.builder()
                        .email(this.userLoggerService.getUserLogger())
                        .password(password)
                .build());
    }
}