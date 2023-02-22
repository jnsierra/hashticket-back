package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.CountryEntity;
import co.ud.hashticket.datos.repository.CountryRepository;
import co.ud.hashticket.datos.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryEntity> findAll() {
        return this.countryRepository.findAll();
    }
}
