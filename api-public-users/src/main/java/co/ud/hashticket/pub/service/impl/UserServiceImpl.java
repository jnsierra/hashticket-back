package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.hashticket.datos.entity.UserTypeEntity;
import co.ud.hashticket.datos.repository.UserRepository;
import co.ud.hashticket.pub.mapper.UserMapper;
import co.ud.hashticket.pub.service.UserService;
import co.ud.ud.hashticket.dto.UsuarioDto;
import co.ud.ud.hashticket.enumeration.USER_STATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UsuarioDto saveUserPublic(UsuarioDto usuarioDto) {
        usuarioDto.setAttempts("0");
        usuarioDto.setChangePassword("S");
        usuarioDto.setState(USER_STATE.ACTIVE);
        usuarioDto.setId(null);
        usuarioDto.setCode("000");
        usuarioDto.setPassword("123456");
        UserEntity user = UserMapper.INSTANCE.map(usuarioDto);
        return UserMapper.INSTANCE.map(save(user));
    }
    private UserEntity save(UserEntity userEntity){
        userEntity.setCreatedBy("external_user");
        userEntity.setLastModifiedBy("external_user");
        userEntity.setCreatedDate(LocalDate.now());
        userEntity.setLastModifiedDate(LocalDate.now());
        userEntity.setUserTypes(new HashSet<>(Arrays.asList(UserTypeEntity.builder().id(3L).build())));
        return userRepository.save(userEntity);
    }
}