package co.ud.hashticket.pub.controller;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.hashticket.pub.mapper.EventMapper;
import co.ud.hashticket.pub.service.EventService;
import co.ud.ud.hashticket.dto.EventDto;
import co.ud.ud.hashticket.dto.EventMoreInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/event")
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<EventDto>> getActiveEvent(){
        Set<EventEntity> entities = eventService.getAllActive();
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(EventMapper.INSTANCE.map(entities));
    }
    @Transactional
    @GetMapping(value = "/{id}/presentation/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventMoreInfoDto> getEventById(@PathVariable(value = "id")Long id, @PathVariable(value = "idPresentation")Long idPresentation){
        Optional<EventEntity> entity= eventService.getByIdAndPresentationId(id, idPresentation);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(EventMapper.INSTANCE.mapMoreInfo(entity.get()));
    }
}