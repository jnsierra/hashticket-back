package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ZoneConfigEventMapper {
    ZoneConfigEventMapper INSTANCE = Mappers.getMapper(ZoneConfigEventMapper.class);
    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "configEvent.id", target = "configEventId")
    ZoneConfigEventDto map(ZoneConfigEventEntity zoneConfigEvent);
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(source = "configEventId", target = "configEvent.id")
    ZoneConfigEventEntity map(ZoneConfigEventDto zoneConfigEvent);
}