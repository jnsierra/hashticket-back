package co.ud.hashticket.pub.mapper;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import co.ud.ud.hashticket.dto.ZoneConfigEventViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ZoneConfigEventMapper {
    ZoneConfigEventMapper INSTANCE = Mappers.getMapper(ZoneConfigEventMapper.class);
    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "configEvent.id", target = "configEventId")
    ZoneConfigEventDto map(ZoneConfigEventEntity zoneConfigEvent);
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(source = "configEventId", target = "configEvent.id")
    ZoneConfigEventEntity map(ZoneConfigEventDto zoneConfigEvent);

    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "configEvent.id", target = "configEventId")
    Set<ZoneConfigEventDto> map(Set<ZoneConfigEventEntity> zoneConfigEvent);
    @Mapping(source = "configEvent.id", target = "configEventId")
    @Mapping(source = "zone.category.name", target = "zone.categoryName")
    @Mapping(source = "zone.category.id", target = "zone.categoryId")
    ZoneConfigEventViewDto mapToView(ZoneConfigEventEntity zoneConfigEvent);

}