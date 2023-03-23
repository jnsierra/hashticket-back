package co.ud.hashticket.pub.controller;

import co.ud.hashticket.datos.entity.PresentationEntity;
import co.ud.hashticket.pub.mapper.PresentationMapper;
import co.ud.hashticket.pub.service.PresentationService;
import co.ud.ud.hashticket.dto.PresentationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v.1/presentation")
public class PresentationController {
    private final PresentationService presentationService;
    @Autowired
    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }
    @GetMapping(value = "/event/{idEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PresentationDto>> getByEvent(@PathVariable(value = "idEvent")Long idEvent){
        Set<PresentationEntity> presentations = presentationService.findByEvent(idEvent);
        if(presentations.isEmpty()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(PresentationMapper.INSTANCE.map(presentations));
    }
}
