package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.CityEntity;
import co.ud.hashticket.datos.entity.CityPkEntity;
import co.ud.hashticket.datos.repository.CityRepository;
import co.ud.hashticket.datos.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @Override
    public Optional<CityEntity> getById(Long cityCode,Long departmentCode) {
        return cityRepository.findById(new CityPkEntity(cityCode,departmentCode));
    }
    @Override
    public Set<CityEntity> getCitiesByDepartment(Long departmentCode) {
        return cityRepository.findByDepartment(departmentCode);
    }
}
