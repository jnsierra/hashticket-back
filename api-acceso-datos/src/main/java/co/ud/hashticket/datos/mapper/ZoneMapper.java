package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.ZoneEntity;
import co.ud.ud.hashticket.dto.ZoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);
    ZoneEntity map(ZoneDto zoneDto);
    ZoneDto map(ZoneEntity zoneEntity);
}
