package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.CountryEntity;

import java.util.Set;

public interface CountryService {

    Set<CountryEntity> findAll();
}
