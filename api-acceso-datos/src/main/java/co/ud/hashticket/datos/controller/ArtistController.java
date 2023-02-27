package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.ArtistEntity;
import co.ud.hashticket.datos.mapper.ArtistMapper;
import co.ud.hashticket.datos.service.ArtistService;
import co.ud.ud.hashticket.dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/artist")
public class ArtistController {
    private final ArtistService artistService;
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistDto> get(@PathVariable(value = "id") Long id){
        Optional<ArtistEntity> response = artistService.getById(id);
        if(!response.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ArtistMapper.INSTANCE.map(response.get()));
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistDto> save(@RequestBody ArtistDto artistDto){
        ArtistEntity entity = ArtistMapper.INSTANCE.map(artistDto);
        return new ResponseEntity<>(ArtistMapper.INSTANCE.map( artistService.save( entity ) ), HttpStatus.CREATED);
    }
}
