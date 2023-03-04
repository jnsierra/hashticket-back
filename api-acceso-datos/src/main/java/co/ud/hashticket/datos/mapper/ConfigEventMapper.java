package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import co.ud.ud.hashticket.dto.ConfigEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConfigEventMapper {
    ConfigEventMapper INSTANCE = Mappers.getMapper(ConfigEventMapper.class);
    @Mapping(source = "eventId", target = "event.id")
    ConfigEventEntity map(ConfigEventDto configEventDto);
    @Mapping(source = "event.id", target = "eventId")
    ConfigEventDto map(ConfigEventEntity configEvent);
}