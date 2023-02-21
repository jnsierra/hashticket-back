package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.hashticket.datos.service.EventService;
import co.ud.ud.hashticket.dto.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v.1/event")
@Slf4j
public class EventController {

    private EventService eventService;
    private ModelMapper modelMapper;
    private UserDetailsService userDetails;
    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper, UserDetailsService userDetails) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
        this.userDetails = userDetails;
    }
    @Secured("NICO")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto){
        EventEntity entity = modelMapper.map(eventDto, EventEntity.class);
        return new ResponseEntity<>(modelMapper.map(eventService.save(entity), EventDto.class) , HttpStatus.CREATED);
    }
}
