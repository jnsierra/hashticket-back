package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.DepartmentEntity;
import co.ud.hashticket.datos.mapper.DepartmentMapper;
import co.ud.hashticket.datos.service.DepartmentService;
import co.ud.ud.hashticket.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<Set<DepartmentDto>> getByCountry(@PathVariable(value = "id") Long idCountry){
        Set<DepartmentEntity> response = departmentService.getSetDepartmentByCountry(idCountry);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity
                .ok().body(DepartmentMapper.INSTANCE.map(response));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable Long id){
        Optional<DepartmentEntity> response = departmentService.getById(id);
        if(!response.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DepartmentDto rto = DepartmentMapper.INSTANCE.map(response.get());
        return ResponseEntity.
                ok().body( rto );

    }
}
