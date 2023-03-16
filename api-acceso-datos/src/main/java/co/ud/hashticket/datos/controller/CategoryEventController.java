package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.CategoryEventEntity;
import co.ud.hashticket.datos.mapper.CategoryEventMapper;
import co.ud.hashticket.datos.service.CategoryEventService;
import co.ud.ud.hashticket.dto.CategoryEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v.1/category_event")
public class CategoryEventController {
    private final CategoryEventService categoryEventService;
    @Autowired
    public CategoryEventController(CategoryEventService categoryEventService) {
        this.categoryEventService = categoryEventService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryEventDto> save(@RequestBody CategoryEventDto categoryEventDto){
        CategoryEventEntity entity = CategoryEventMapper.INSTANCE.map(categoryEventDto);
        return ResponseEntity.ok(CategoryEventMapper.INSTANCE.map(categoryEventService.save(entity)));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryEventDto>> get(){
        return new ResponseEntity<>(CategoryEventMapper.INSTANCE.map(categoryEventService.findAll()), HttpStatus.OK);
    }
}
