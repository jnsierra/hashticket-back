package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import co.ud.ud.hashticket.dto.ConfigEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ConfigEventMapper {
    ConfigEventMapper INSTANCE = Mappers.getMapper(ConfigEventMapper.class);
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "presentationId", target = "presentation.id")
    ConfigEventEntity map(ConfigEventDto configEventDto);
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "presentation.id", target = "presentationId")
    ConfigEventDto map(ConfigEventEntity configEvent);

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "presentation.id", target = "presentationId")
    Set<ConfigEventDto> map(Set<ConfigEventEntity> configEvent);
}