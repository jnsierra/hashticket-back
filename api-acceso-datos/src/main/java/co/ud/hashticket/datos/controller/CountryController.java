package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.CountryEntity;
import co.ud.hashticket.datos.service.CountryService;
import co.ud.ud.hashticket.dto.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v.1/country")
@Slf4j
public class CountryController {

    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryController(CountryService countryService, @Qualifier("defaultMapper") ModelMapper modelMapper) {
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryDto[]> get(){
        List<CountryEntity> result = countryService.findAll();
        return new ResponseEntity<>(modelMapper.map(result, CountryDto[].class), HttpStatus.OK);
    }
}
