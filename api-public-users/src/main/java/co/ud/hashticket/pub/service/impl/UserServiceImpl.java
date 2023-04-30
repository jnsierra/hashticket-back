package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.hashticket.datos.entity.UserTypeEntity;
import co.ud.hashticket.datos.repository.UserRepository;
import co.ud.hashticket.pub.mapper.UserMapper;
import co.ud.hashticket.pub.service.UserService;
import co.ud.hashticket.pub.service.UserTypeService;
import co.ud.ud.hashticket.dto.UsuarioDto;
import co.ud.ud.hashticket.enumeration.USER_STATE;
import co.ud.ud.hashticket.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordServiceImpl passwordService;
    private final EmailServiceImpl emailService;
    private UserTypeService userTypeService;
    private final String USER_TYPE_NEW = "ROLE_UNVERIFIED_USER";
    private Function<UsuarioDto, UsuarioDto> functionUser = user -> UserMapper.INSTANCE.map(user.getEmail()
            , user.getName()
            , "0"
            , "S"
            , USER_STATE.ACTIVE
            , "000");
    private Function<String, Optional<UserTypeEntity>> functionGetUserType = item -> {
        Optional<UserTypeEntity> obj = userTypeService.findByType(item);
        obj.orElseThrow(() -> new BusinessException(2L,"error", String.format("Error finding Type User %s", USER_TYPE_NEW)));
        return obj;
    };
    private Function<UsuarioDto, Boolean> functionCreateUser = functionUser.andThen(this::generatePassword)
            .andThen(UserMapper.INSTANCE::map)
            .andThen(this::save)
            .andThen(this::validateInsert)
            .andThen(this::sendNotification);
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordServiceImpl passwordService, EmailServiceImpl emailService, UserTypeService userTypeService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.emailService = emailService;
        this.userTypeService = userTypeService;
    }
    @Override
    @Transactional
    public Boolean saveUserPublic(UsuarioDto usuarioDto) {
        return functionCreateUser.apply(usuarioDto);
    }
    private UsuarioDto generatePassword(UsuarioDto usuarioDto){
        usuarioDto.setPassword(passwordService.generatePassayPassword(8));
        return usuarioDto;
    }
    private Optional<UserEntity> save(UserEntity userEntity){
        userEntity.setCreatedBy("external_user");
        userEntity.setLastModifiedBy("external_user");
        userEntity.setCreatedDate(LocalDate.now());
        userEntity.setLastModifiedDate(LocalDate.now());
        userEntity.setUserTypes(new HashSet<>(Arrays.asList(this.functionGetUserType.apply(this.USER_TYPE_NEW).get())));
        UserEntity user = userRepository.save(userEntity);
        return Objects.nonNull(user.getId()) ? Optional.of(user): Optional.empty() ;
    }
    private UserEntity validateInsert(Optional<UserEntity> user){
        if(user.isEmpty()){
            throw new BusinessException(2L,"error", "Error inserting user");
        }
        return user.get();
    }
    private String createMessageUser(UserEntity userEntity){
        return String.format("""
            Se ha creado un usuario en https://www.compraboletas.shop/ a continuación de proporcionamos las credenciales para tu ingreso:
            Usuario: %s
            Contraseña: %s
            """, userEntity.getEmail(), userEntity.getPassword());
    }
    private Boolean sendNotification(UserEntity userEntity){
        if( !emailService.sendSimpleMessage(userEntity.getEmail(),"Nuevo Usuario", createMessageUser(userEntity)) ){
            throw new BusinessException(2L,"error", "Error send email notification");
        }
        return Boolean.TRUE;
    }
}