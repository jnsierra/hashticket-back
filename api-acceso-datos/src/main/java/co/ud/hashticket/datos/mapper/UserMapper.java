package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.ud.hashticket.dto.UsuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UsuarioDto map(UserEntity userEntity);
    UserEntity map(UsuarioDto userDto);
}
