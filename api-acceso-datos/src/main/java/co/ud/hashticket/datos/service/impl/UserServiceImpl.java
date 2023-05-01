package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.hashticket.datos.repository.UserRepository;
import co.ud.hashticket.datos.service.UserService;
import co.ud.ud.hashticket.exception.BusinessException;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
    @Override
    @Transactional
    @PreAuthorize("#email == authentication.name")
    public boolean updatePassword(String email, String password) {
        if( userRepository.updatePassword(email, password) != 1 ){
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, String.format("Error al actualizar la contraseña del usuario %s",email));
        }
        return true;
    }
}