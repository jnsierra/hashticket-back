package co.ud.hashticket.pub.controller;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import co.ud.hashticket.pub.mapper.ConfigEventMapper;
import co.ud.hashticket.pub.service.ConfigEventService;
import co.ud.ud.hashticket.dto.ConfigEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/config_event")
public class ConfigEventController {

    private final ConfigEventService configEventService;
    @Autowired
    public ConfigEventController(ConfigEventService configEventService) {
        this.configEventService = configEventService;
    }

    @GetMapping(value = "/event/{idEvent}/presentation/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEventDto> get(@PathVariable(value = "idEvent")Long idEvent, @PathVariable(value = "idPresentation")Long idPresentation){
        Optional<ConfigEventEntity> entity = configEventService.findByEventIdAndPresentationId(idEvent, idPresentation);
        if(!entity.isPresent()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(ConfigEventMapper.INSTANCE.map(entity.get()));
    }
}
