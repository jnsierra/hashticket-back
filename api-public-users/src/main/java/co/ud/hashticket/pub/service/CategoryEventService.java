package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.CategoryEventEntity;

import java.util.Optional;
import java.util.Set;

public interface CategoryEventService {
    Optional<CategoryEventEntity> findById(Long id);
    Set<CategoryEventEntity> getAll();
}
