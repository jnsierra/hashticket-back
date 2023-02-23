package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.CountryEntity;

import java.util.List;

public interface CountryService {

    List<CountryEntity> findAll();
}
