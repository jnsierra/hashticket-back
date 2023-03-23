package co.ud.hashticket.pub.mapper;

import co.ud.hashticket.datos.entity.PresentationEntity;
import co.ud.ud.hashticket.dto.PresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface PresentationMapper {
    PresentationMapper INSTANCE = Mappers.getMapper(PresentationMapper.class);
    @Mapping(source = "event.id", target = "eventId")
    PresentationDto map(PresentationEntity presentationEntity);
    @Mapping(source = "event.id", target = "eventId")
    Set<PresentationDto> map(Set<PresentationEntity> presentations);
}
