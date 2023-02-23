package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.DepartmentEntity;
import co.ud.hashticket.datos.service.DepartmentService;
import co.ud.ud.hashticket.dto.DepartmentDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/department")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;
    @Autowired
    public DepartmentController(DepartmentService departmentService,@Qualifier("defaultMapper") ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<DepartmentDto[]> getByCountry(@PathVariable(value = "id") Long idCountry){
        Set<DepartmentEntity> response = departmentService.getSetDepartmentByCountry(idCountry);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity
                .ok().body(modelMapper.map(response, DepartmentDto[].class));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable Long id){
        Optional<DepartmentEntity> response = departmentService.getById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.
                ok().body(modelMapper.map(response.get(), DepartmentDto.class ));
    }
}
