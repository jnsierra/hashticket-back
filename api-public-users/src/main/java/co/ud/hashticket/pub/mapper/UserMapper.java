package co.ud.hashticket.pub.mapper;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.ud.hashticket.dto.UsuarioDto;
import co.ud.ud.hashticket.enumeration.USER_STATE;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UsuarioDto map(UserEntity userEntity);
    UserEntity map(UsuarioDto userDto);
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "attempts", target = "attempts")
    @Mapping(source = "changePassword", target = "changePassword")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "code", target = "code")
    UsuarioDto map(String email, String name, String attempts, String changePassword, USER_STATE state, String code);
}