package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.PresentationEntity;
import co.ud.ud.hashticket.dto.PresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PresentationMapper {
    PresentationMapper INSTANCE = Mappers.getMapper(PresentationMapper.class);
    @Mapping(source = "eventId", target = "event.id")
    PresentationEntity map(PresentationDto presentationDto);
    @Mapping(source = "event.id", target = "eventId")
    PresentationDto map(PresentationEntity presentation);
}
