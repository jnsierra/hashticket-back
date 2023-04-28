package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.hashticket.datos.entity.UserTypeEntity;
import co.ud.hashticket.datos.repository.UserRepository;
import co.ud.hashticket.pub.mapper.UserMapper;
import co.ud.hashticket.pub.service.UserService;
import co.ud.ud.hashticket.dto.UsuarioDto;
import co.ud.ud.hashticket.enumeration.USER_STATE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
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
        Optional<UserEntity> userEntity = save(user);
        if(userEntity.isPresent()){
            log.info("USER-INSERT|{}",usuarioDto.getEmail());
            return UserMapper.INSTANCE.map(userEntity.get());
        }
        return null;
    }
    private Optional<UserEntity> save(UserEntity userEntity){
        userEntity.setCreatedBy("external_user");
        userEntity.setLastModifiedBy("external_user");
        userEntity.setCreatedDate(LocalDate.now());
        userEntity.setLastModifiedDate(LocalDate.now());
        userEntity.setUserTypes(new HashSet<>(Arrays.asList(UserTypeEntity.builder().id(3L).build())));
        UserEntity user = userRepository.save(userEntity);
        return Objects.nonNull(user.getId()) ? Optional.of(user): Optional.empty() ;
    }
}