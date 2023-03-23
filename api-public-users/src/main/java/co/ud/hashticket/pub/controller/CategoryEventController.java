package co.ud.hashticket.pub.controller;

import co.ud.hashticket.datos.entity.CategoryEventEntity;
import co.ud.hashticket.pub.mapper.CategoryEventMapper;
import co.ud.hashticket.pub.service.CategoryEventService;
import co.ud.ud.hashticket.dto.CategoryEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/category_event")
public class CategoryEventController {
    private final CategoryEventService categoryEventService;
    @Autowired
    public CategoryEventController(CategoryEventService categoryEventService) {
        this.categoryEventService = categoryEventService;
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryEventDto> findById(@PathVariable(value = "id")Long id){
        Optional<CategoryEventEntity> entity = categoryEventService.findById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(CategoryEventMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryEventDto>> getAll(){
        Set<CategoryEventEntity> response = categoryEventService.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(CategoryEventMapper.INSTANCE.map(response));
    }
}
