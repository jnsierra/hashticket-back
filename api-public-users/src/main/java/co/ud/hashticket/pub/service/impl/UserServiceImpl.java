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
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import freemarker.template.Configuration;
import freemarker.template.Template;
import javax.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordServiceImpl passwordService;
    private final EmailServiceImpl emailService;
    private UserTypeService userTypeService;
    private static final String USER_TYPE_NEW = "ROLE_UNVERIFIED_USER";
    private UnaryOperator<UsuarioDto> functionUser = user -> UserMapper.INSTANCE.map(user.getEmail()
            , user.getName()
            , "0"
            , "S"
            , USER_STATE.ACTIVE
            , "000");
    private Function<String, Optional<UserTypeEntity>> functionGetUserType = item -> {
        Optional<UserTypeEntity> obj = userTypeService.findByType(item);
        obj.orElseThrow(() -> new BusinessException(2L,  TYPE_EXCEPTION.ERROR, String.format("Error finding Type User %s", USER_TYPE_NEW)));
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
            throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "Error inserting user");
        }
        return user.get();
    }
    private boolean sendNotification(UserEntity userEntity){
        try {
            if(!emailService.sendHtmlMessage(userEntity.getEmail(), "Nueva cuenta", this.getTemplate(userEntity.getEmail(), userEntity.getPassword()))){
                throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "Error send email notification");
            }
        } catch (MessagingException e) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al enviar la notificacion por correo",e);
        }
        return Boolean.TRUE;
    }

    private String getTemplate(String email, String password){
        try {
            Configuration config = new Configuration(Configuration.VERSION_2_3_31);
            config.setClassForTemplateLoading(getClass(), "/");
            Template template = config.getTemplate("bienvenida.ftl");
            Map<String, Object> datos = new HashMap<>();
            datos.put("email", email);
            datos.put("password", password);
            StringWriter sw = new StringWriter();
            template.process(datos, sw);
            return sw.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Ok";
    }
}