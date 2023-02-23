package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.CityEntity;
import co.ud.hashticket.datos.service.CityService;
import co.ud.ud.hashticket.dto.CityDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/city")
@Slf4j
public class CityController {
    private final CityService cityService;
    private final ModelMapper modelMapper;
    @Autowired
    public CityController(CityService cityService,@Qualifier("cityMapper") ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }
    @GetMapping(value = "/{code}/{departmentCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityDto> get(@PathVariable(value = "code") Long code, @PathVariable(value = "departmentCode") Long departmentCode){
        Optional<CityEntity> response = cityService.getById(code, departmentCode);
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(modelMapper.map(response.get(), CityDto.class));
    }
    @GetMapping(value = "/department/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityDto[]> getByDepartment(@PathVariable(value = "id") Long departmentCode){
        Set<CityEntity> response = cityService.getCitiesByDepartment(departmentCode);
        if(response.isEmpty()){
            return ResponseEntity
                    .noContent().build();
        }
        return ResponseEntity.ok(modelMapper.map(response, CityDto[].class));
    }
}