package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.CategoryEntity;
import co.ud.hashticket.datos.repository.CategoryRepository;
import co.ud.hashticket.pub.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Set<CategoryEntity> getAll() {
        return new HashSet<>(categoryRepository.findAll());
    }
    @Override
    public Set<CategoryEntity> getByEventAndPresentation(Long idEvent, Long idPresentation) {
        return categoryRepository.getByEventAndPresentation(idEvent, idPresentation);
    }
}
