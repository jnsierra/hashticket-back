package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.mapper.CountryMapper;
import co.ud.hashticket.datos.service.CountryService;
import co.ud.ud.hashticket.dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v.1/country")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CountryDto>> get(){
        return new ResponseEntity<>(CountryMapper.INSTANCE.map(countryService.findAll()), HttpStatus.OK);
    }
}
