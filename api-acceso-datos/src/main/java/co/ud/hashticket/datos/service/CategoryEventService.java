package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.CategoryEventEntity;

import java.util.Optional;
import java.util.Set;

public interface CategoryEventService {

    CategoryEventEntity save(CategoryEventEntity event);
    Set<CategoryEventEntity> findAll();
    Optional<CategoryEventEntity> findById(Long id);
}
