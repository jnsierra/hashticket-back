package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.hashticket.datos.mapper.EventMapper;
import co.ud.hashticket.datos.service.EventService;
import co.ud.ud.hashticket.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService ) {
        this.eventService = eventService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto){
        EventEntity entity = EventMapper.INSTANCE.map(eventDto);
        return new ResponseEntity<>(EventMapper.INSTANCE.map( eventService.save( entity ) ), HttpStatus.CREATED);
    }


}
