package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import co.ud.hashticket.datos.mapper.ZoneConfigEventMapper;
import co.ud.hashticket.datos.service.ZoneConfigEventService;
import co.ud.ud.hashticket.dto.ZoneConfigEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/zone_config_event")
public class ZoneConfigEventController {
    private ZoneConfigEventService zoneConfigEventService;
    @Autowired
    public ZoneConfigEventController(ZoneConfigEventService zoneConfigEventService) {
        this.zoneConfigEventService = zoneConfigEventService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ZoneConfigEventDto> save(@RequestBody ZoneConfigEventDto zoneDto){
        ZoneConfigEventEntity entity = ZoneConfigEventMapper.INSTANCE.map(zoneDto);
        return ResponseEntity.ok(ZoneConfigEventMapper.INSTANCE.map( zoneConfigEventService.save(entity) ));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZoneConfigEventDto> getById(@PathVariable(value = "id")Long id){
        Optional<ZoneConfigEventEntity> entity = zoneConfigEventService.getById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneConfigEventMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping(value = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ZoneConfigEventDto>> getByIdEvent(@PathVariable(value = "id")Long idEvent){
        Set<ZoneConfigEventEntity> response = zoneConfigEventService.getZoneConfigByEvent(idEvent);
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneConfigEventMapper.INSTANCE.map(response));
    }
    @GetMapping(value = "/event/{event_id}/presentation/{presentation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ZoneConfigEventDto>> getByIdEventAndPresentation(@PathVariable(value = "event_id")Long idEvent, @PathVariable(value = "presentation_id")Long idPresentation ){
        Set<ZoneConfigEventEntity> response = zoneConfigEventService.getZoneConfigByEventAndPresentation(idEvent, idPresentation);
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneConfigEventMapper.INSTANCE.map(response));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Boolean> updateCreateTickets(@PathVariable(value = "id")Long id){
        return ResponseEntity.ok(zoneConfigEventService.updateCreateTickets(id));
    }
}