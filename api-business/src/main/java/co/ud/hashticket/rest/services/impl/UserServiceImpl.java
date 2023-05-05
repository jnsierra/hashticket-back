package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.UserTicketsClient;
import co.ud.hashticket.rest.services.UserService;
import co.ud.hashticket.security.service.UserLoggerService;
import co.ud.ud.hashticket.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {
    private UserTicketsClient userTicketsClient;
    private final UserLoggerService userLoggerService;
    private Function<UsuarioDto, Boolean> functionUpdate = user -> userTicketsClient.updatePasswordUser(user);
    private Predicate<String> isDeletedUserType = email -> userTicketsClient.deleteUserTypeByEmail(email);
    private Predicate<UsuarioDto> isAddUserType = user -> userTicketsClient.addUserTypeByEmail(user, ROLE_GENERIC);
    private static final String ROLE_GENERIC = "ROLE_USER_GENERIC";
    @Autowired
    public UserServiceImpl(UserTicketsClient userTicketsClient, UserLoggerService userLoggerService) {
        this.userTicketsClient = userTicketsClient;
        this.userLoggerService = userLoggerService;
    }

    @Override
    public boolean changePassword(String password) {
        UsuarioDto user = UsuarioDto.builder()
                .email(this.userLoggerService.getUserLogger())
                .password(password)
                .build();
        Function<UsuarioDto, Boolean> execute = functionUpdate.andThen(item -> this.deleteRoles(item, this.userLoggerService.getUserLogger()))
                .andThen(item -> this.deleteRoles(item, this.userLoggerService.getUserLogger()))
                .andThen(item -> deleteRoles(item, this.userLoggerService.getUserLogger()))
                .andThen(item -> addRoleGeneric(item, user));
        return execute.apply(user);


    }
    private boolean deleteRoles(boolean response, String email) {
        return response ? isDeletedUserType.test(email) : false;
    }
    private boolean addRoleGeneric(boolean response, UsuarioDto usuarioDto){
        return response ? isAddUserType.test(usuarioDto) : false;
    }
}