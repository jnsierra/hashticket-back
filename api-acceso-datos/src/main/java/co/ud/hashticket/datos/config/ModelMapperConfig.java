package co.ud.hashticket.datos.config;

import co.ud.hashticket.datos.entity.CityEntity;
import co.ud.ud.hashticket.dto.CityDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean("defaultMapper")
    public ModelMapper getMapper(){
        return new ModelMapper();
    }

    @Bean("cityMapper")
    public ModelMapper getMapperCity(){
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<CityEntity, CityDto> personMap = new PropertyMap<CityEntity, CityDto>() {
            protected void configure() {
                map().setCode(source.getCityPk().getCode());
                map().setName(source.getName());
                map().setDepartmentCode(source.getCityPk().getDepartmentCode());
            }
        };
        modelMapper.addMappings(personMap);
        return modelMapper;
    }

}
