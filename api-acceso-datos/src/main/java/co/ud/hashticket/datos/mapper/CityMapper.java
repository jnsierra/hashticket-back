package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.CityEntity;
import co.ud.ud.hashticket.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    @Mapping(source = "cityPk.code", target = "code")
    @Mapping(source = "cityPk.departmentCode", target = "departmentCode")
    CityDto map(CityEntity city);
    @Mapping(source = "cityPk.code", target = "code")
    @Mapping(source = "cityPk.departmentCode", target = "departmentCode")
    Set<CityDto> map(Set<CityEntity> cities);
}
