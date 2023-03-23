package co.ud.hashticket.pub.mapper;

import co.ud.hashticket.datos.entity.CategoryEventEntity;
import co.ud.ud.hashticket.dto.CategoryEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CategoryEventMapper {
    CategoryEventMapper INSTANCE = Mappers.getMapper(CategoryEventMapper.class);
    CategoryEventDto map(CategoryEventEntity categoryEvent);
    CategoryEventEntity map(CategoryEventDto categoryEventDto);
    Set<CategoryEventDto> map(Set<CategoryEventEntity> categoryEventEntities);
}
