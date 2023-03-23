package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.CategoryEventEntity;
import co.ud.hashticket.datos.repository.CategoryEventRepository;
import co.ud.hashticket.pub.service.CategoryEventService;
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
    public Optional<CategoryEventEntity> findById(Long id) {
        return categoryEventRepository.findById(id);
    }
    @Override
    public Set<CategoryEventEntity> getAll() {
        return new HashSet<>(categoryEventRepository.findAll());
    }
}