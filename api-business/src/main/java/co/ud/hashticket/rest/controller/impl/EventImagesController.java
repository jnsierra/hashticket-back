package co.ud.hashticket.rest.controller.impl;

import co.ud.hashticket.rest.services.EventImagesService;
import co.ud.ud.hashticket.dto.EventImagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        EventImagesDto entity = eventImagesService.save(eventImagesDto);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventImagesDto> getById(@PathVariable(value = "id")Long id){
        EventImagesDto entity = eventImagesService.getById(id);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
}