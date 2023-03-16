package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.EventImagesEntity;
import co.ud.hashticket.datos.mapper.EventImagesMapper;
import co.ud.hashticket.datos.service.EventImagesService;
import co.ud.ud.hashticket.dto.EventImagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v.1/event_images")
public class EventImagesController {
    private final EventImagesService eventImagesService;
    @Autowired
    public EventImagesController(EventImagesService eventImagesService) {
        this.eventImagesService = eventImagesService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventImagesDto> save(@RequestBody EventImagesDto eventImagesDto){
        EventImagesEntity entity = EventImagesMapper.INSTANCE.map(eventImagesDto);
        return new ResponseEntity<>(EventImagesMapper.INSTANCE.map( eventImagesService.save( entity ) ), HttpStatus.CREATED);
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<EventImagesDto>> getAll(){
        Set<EventImagesEntity> entities = eventImagesService.findAll();
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(EventImagesMapper.INSTANCE.map(entities));
    }
}