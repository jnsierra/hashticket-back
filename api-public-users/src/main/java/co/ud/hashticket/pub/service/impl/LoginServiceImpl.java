package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.hashticket.datos.repository.UserRepository;
import co.ud.hashticket.pub.service.LoginService;
import co.ud.ud.hashticket.enumeration.LOGIN_ACTION;
import co.ud.ud.hashticket.enumeration.USER_STATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    @Autowired
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LOGIN_ACTION validaLogin(String email, String pass) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            return LOGIN_ACTION.USER_NOT_FOUND;
        }
        if(!this.validateStateUser(user.get())){
            return LOGIN_ACTION.USER_BLOKED;
        }
        if(!this.validatePassword(email, pass, user.get())){
            return LOGIN_ACTION.PASSWORD_INCORRECT;
        }
        if(!this.validateChagePass(user.get())){
            return LOGIN_ACTION.SUCCESS_CHANGE_PASSWORD;
        }
        return LOGIN_ACTION.SUCCESS;
    }

    private Boolean validateStateUser(UserEntity user) {
        if( USER_STATE.ACTIVE.equals(user.getState()) ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean validatePassword(String email, String pass, UserEntity userEntity) {
        Optional<UserEntity> user = userRepository.findByEmailAndPassword(email, pass);
        if(user.isPresent()){
            return Boolean.TRUE;
        }
        //En el caso de ser incorrecto el usuario y contraseña se contara un intento de ingreso
        userRepository.updateAttempts(userEntity.getId(), userEntity.getAttempts() + 1L);
        return Boolean.FALSE;
    }

    private Boolean validateChagePass(UserEntity user) {
        if("S".equals(user.getChangePassword())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
