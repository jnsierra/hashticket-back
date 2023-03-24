package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.ArtistEntity;
import co.ud.ud.hashticket.dto.ArtistDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ArtistMapper {
    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);
    @Mapping(source = "musicBand.id", target = "musicBandId")
    ArtistDto map(ArtistEntity artistEntity);
    @Mapping(source = "musicBandId", target = "musicBand.id")
    ArtistEntity map(ArtistDto artistDto);
    @Mapping(source = "musicBand.id", target = "musicBandId")
    Set<ArtistDto> map(Set<ArtistEntity> artistEntity);

}
