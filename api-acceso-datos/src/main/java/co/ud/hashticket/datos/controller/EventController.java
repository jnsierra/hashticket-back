package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.hashticket.datos.service.EventService;
import co.ud.ud.hashticket.dto.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/event")
@Slf4j
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;
    @Autowired
    public EventController(EventService eventService, @Qualifier("defaultMapper") ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto){
        EventEntity entity = modelMapper.map(eventDto, EventEntity.class);
        return new ResponseEntity<>(modelMapper.map(eventService.save(entity), EventDto.class) , HttpStatus.CREATED);
    }
}
