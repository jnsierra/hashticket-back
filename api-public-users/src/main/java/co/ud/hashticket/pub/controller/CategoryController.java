package co.ud.hashticket.pub.controller;

import co.ud.hashticket.datos.entity.CategoryEntity;
import co.ud.hashticket.pub.mapper.CategoryMapper;
import co.ud.hashticket.pub.service.CategoryService;
import co.ud.ud.hashticket.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v.1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping(value = "/event/{idEvent}/presentation/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryDto>> getByEvent(@PathVariable(value = "idEvent")Long idEvent, @PathVariable(value = "idPresentation")Long idPresentation){
        Set<CategoryEntity> entities = categoryService.getByEventAndPresentation(idEvent, idPresentation);
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CategoryMapper.INSTANCE.map(entities));
    }
}
