package co.ud.hashticket.pub.mapper;

import co.ud.hashticket.datos.entity.EventImagesEntity;
import co.ud.ud.hashticket.dto.EventImagesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface EventImagesMapper {
    EventImagesMapper INSTANCE = Mappers.getMapper(EventImagesMapper.class);
    @Mapping(source = "event.id", target = "eventId")
    EventImagesDto map(EventImagesEntity eventImages);
    @Mapping(source = "eventId", target = "event.id")
    EventImagesEntity map(EventImagesDto eventImagesDto);
    @Mapping(source = "event.id", target = "eventId")
    Set<EventImagesDto> map(Set<EventImagesEntity> eventImages);
}
