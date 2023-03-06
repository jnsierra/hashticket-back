package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.PresentationEntity;
import co.ud.hashticket.datos.mapper.PresentationMapper;
import co.ud.hashticket.datos.service.PresentationService;
import co.ud.ud.hashticket.dto.PresentationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/presentation")
public class PresentationController {
    private final PresentationService presentationService;
    @Autowired
    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }
    @GetMapping( value = "/{id}")
    public ResponseEntity<PresentationDto> get(@PathVariable("id")Long id){
        Optional<PresentationEntity> entity = presentationService.getById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(PresentationMapper.INSTANCE.map(entity.get()));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PresentationDto> save(@RequestBody PresentationDto presentationDto){
        PresentationEntity entity = PresentationMapper.INSTANCE.map(presentationDto);
        return ResponseEntity.ok(PresentationMapper.INSTANCE.map(presentationService.save(entity)));
    }
}