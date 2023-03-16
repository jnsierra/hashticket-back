package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.CategoryEventEntity;
import co.ud.hashticket.datos.repository.CategoryEventRepository;
import co.ud.hashticket.datos.service.CategoryEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class CategoryEventServiceImpl implements CategoryEventService {
    private final CategoryEventRepository categoryEventRepository;
    @Autowired
    public CategoryEventServiceImpl(CategoryEventRepository categoryEventRepository) {
        this.categoryEventRepository = categoryEventRepository;
    }

    @Override
    public CategoryEventEntity save(CategoryEventEntity event) {
        return categoryEventRepository.save(event);
    }

    @Override
    public Set<CategoryEventEntity> findAll() {
        return new HashSet<>(categoryEventRepository.findAll());
    }

    @Override
    public Optional<CategoryEventEntity> findById(Long id) {
        return categoryEventRepository.findById(id);
    }
}
