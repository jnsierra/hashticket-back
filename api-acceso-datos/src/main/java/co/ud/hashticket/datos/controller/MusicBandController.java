package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.MusicBandEntity;
import co.ud.hashticket.datos.mapper.MusicBandMapper;
import co.ud.hashticket.datos.service.impl.MusicBandServiceImpl;
import co.ud.ud.hashticket.dto.MusicBandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/musicBand")
public class MusicBandController {
    private MusicBandServiceImpl musicBandService;
    @Autowired
    public MusicBandController(MusicBandServiceImpl musicBandService) {
        this.musicBandService = musicBandService;
    }
    @GetMapping( value = "/{id}")
    public ResponseEntity<MusicBandDto> get(@PathVariable("id")Long id){
        Optional<MusicBandEntity> entity = musicBandService.getById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MusicBandMapper.INSTANCE.map(entity.get()));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MusicBandDto> save(@RequestBody MusicBandDto musicBandDto){
        MusicBandEntity entity = MusicBandMapper.INSTANCE.map(musicBandDto);
        return ResponseEntity.ok(MusicBandMapper.INSTANCE.map(musicBandService.save(entity)));
    }
}
