package co.ud.hashticket.pub.mapper;

import co.ud.hashticket.datos.entity.ZoneEntity;
import co.ud.ud.hashticket.dto.ZoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);
    @Mapping(source = "categoryId", target = "category.id")
    ZoneEntity map(ZoneDto zoneDto);
    @Mapping(source = "category.id", target = "categoryId")
    ZoneDto map(ZoneEntity zoneEntity);
    @Mapping(source = "category.id", target = "categoryId")
    Set<ZoneDto> map(Set<ZoneEntity> zoneEntity);
}
