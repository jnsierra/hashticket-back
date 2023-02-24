package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.DepartmentEntity;
import co.ud.ud.hashticket.dto.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper  INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    Set<DepartmentDto> map(Set<DepartmentEntity> department);

    @Mapping(source = "country.code", target = "countryCode")
    DepartmentDto map(DepartmentEntity department);
}
