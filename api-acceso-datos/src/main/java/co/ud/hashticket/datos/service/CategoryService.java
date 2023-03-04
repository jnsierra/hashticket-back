package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.CategoryEntity;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    CategoryEntity save(CategoryEntity category);
    Set<CategoryEntity> findAll();
    Optional<CategoryEntity> findById(Long id);
}
