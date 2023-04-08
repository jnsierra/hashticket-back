package co.ud.hashticket.pub.mapper;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.ud.hashticket.dto.EventDto;
import co.ud.ud.hashticket.dto.EventMoreInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = {PresentationMapper.class, ConfigEventMapper.class})
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    @Mapping(source = "cityCode", target = "city.cityPk.code")
    @Mapping(source = "departmentCode", target = "city.cityPk.departmentCode")
    @Mapping(source = "eventStatus", target = "eventStatus")
    @Mapping(source = "categoryEventId", target = "categoryEvent.id")
    EventEntity map(EventDto eventDto);
    @Mapping(source = "city.cityPk.code", target = "cityCode")
    @Mapping(source = "city.cityPk.departmentCode", target = "departmentCode")
    @Mapping(source = "categoryEvent.id", target = "categoryEventId")
    EventDto map(EventEntity event);
    @Mapping(source = "city.cityPk.code", target = "cityCode")
    @Mapping(source = "city.cityPk.departmentCode", target = "departmentCode")
    @Mapping(source = "categoryEvent.id", target = "categoryEventId")
    Set<EventDto> map(Set<EventEntity> department);
    @Mapping(source = "city.name", target = "cityName")
    @Mapping(source = "city.department.name", target = "departmentName")
    @Mapping(source = "city.department.country.name", target = "countyName")
    @Mapping(source = "categoryEvent.name", target = "categoryEventName")
    EventMoreInfoDto mapMoreInfo(EventEntity event);
}