package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.CountryEntity;
import co.ud.ud.hashticket.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CountryMapper {

    CountryMapper  INSTANCE = Mappers.getMapper(CountryMapper.class);
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    Set<CountryDto> map(Set<CountryEntity> country);
}
