package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.CategoryEntity;

import java.util.Set;

public interface CategoryService {
    Set<CategoryEntity> getAll();
    Set<CategoryEntity> getByEventAndPresentation(Long idEvent, Long idPresentation);
}
