package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.MusicBandEntity;
import co.ud.ud.hashticket.dto.MusicBandDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MusicBandMapper {
    MusicBandMapper INSTANCE = Mappers.getMapper(MusicBandMapper.class);
    @Mapping(source = "presentationId", target = "presentation.id")
    MusicBandEntity map(MusicBandDto musicBandDto);
    @Mapping(source = "presentation.id", target = "presentationId")
    MusicBandDto map(MusicBandEntity musicBand);
}
