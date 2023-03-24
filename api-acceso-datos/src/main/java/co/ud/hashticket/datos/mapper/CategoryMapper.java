package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.CategoryEntity;
import co.ud.ud.hashticket.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto map(CategoryEntity categoryDto);
    CategoryEntity map(CategoryDto categoryDto);
    Set<CategoryDto> map(Set<CategoryEntity> categoryDto);
}
