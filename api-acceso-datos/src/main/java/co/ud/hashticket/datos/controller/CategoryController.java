package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.CategoryEntity;
import co.ud.hashticket.datos.mapper.CategoryMapper;
import co.ud.hashticket.datos.service.CategoryService;
import co.ud.ud.hashticket.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto){
        CategoryEntity entity = CategoryMapper.INSTANCE.map(categoryDto);
        return new ResponseEntity<>(CategoryMapper.INSTANCE.map( categoryService.save( entity ) ), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getById(@PathVariable(value = "id")Long id){
        Optional<CategoryEntity> entity = categoryService.findById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CategoryMapper.INSTANCE.map( entity.get() ));
    }
}
