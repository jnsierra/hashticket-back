package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.DepartmentEntity;
import co.ud.hashticket.datos.repository.DepartmentRepository;
import co.ud.hashticket.datos.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    public Set<DepartmentEntity> getSetDepartmentByCountry(Long id) {
        return departmentRepository.findByCountry(id);
    }
    @Override
    public Optional<DepartmentEntity> getById(Long id) {
        return departmentRepository.findById(id);
    }
}
