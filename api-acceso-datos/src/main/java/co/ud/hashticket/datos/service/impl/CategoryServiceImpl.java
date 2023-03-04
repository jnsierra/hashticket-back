package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.CategoryEntity;
import co.ud.hashticket.datos.repository.CategoryRepository;
import co.ud.hashticket.datos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }
    @Override
    public Set<CategoryEntity> findAll() {
        return new HashSet<>(categoryRepository.findAll());
    }
    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return categoryRepository.findById(id);
    }
}