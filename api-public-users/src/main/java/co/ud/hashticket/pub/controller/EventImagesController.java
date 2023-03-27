package co.ud.hashticket.pub.controller;

import co.ud.hashticket.pub.service.EventImagesService;
import co.ud.ud.hashticket.dto.EventImagesDto;
import co.ud.ud.hashticket.enumeration.TypeImages;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(value = "/event/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<EventImagesDto>> getEventImagesByEventAndType(@PathVariable(value = "id") Long id, @RequestParam TypeImages typeImages){
        Set<EventImagesDto> images = eventImagesService.findByEventAndTypeImages(id, typeImages);
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);

    }
}
