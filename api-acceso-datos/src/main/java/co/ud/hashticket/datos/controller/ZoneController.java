package co.ud.hashticket.datos.controller;

import co.ud.hashticket.datos.entity.ZoneEntity;
import co.ud.hashticket.datos.mapper.ZoneMapper;
import co.ud.hashticket.datos.service.ZoneService;
import co.ud.ud.hashticket.dto.ZoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/zone")
public class ZoneController {
    private final ZoneService zoneService;
    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ZoneDto> save(@RequestBody ZoneDto zoneDto){
        ZoneEntity entity = ZoneMapper.INSTANCE.map(zoneDto);
        return ResponseEntity.ok(ZoneMapper.INSTANCE.map( zoneService.save(entity) ));
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZoneDto> getById(@PathVariable(value = "id")Long id){
        Optional<ZoneEntity> entity = zoneService.getById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneMapper.INSTANCE.map(entity.get()));
    }
}
