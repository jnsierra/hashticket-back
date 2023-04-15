package co.ud.hashticket.pub.controller;

import co.ud.hashticket.datos.entity.ZoneEntity;
import co.ud.hashticket.pub.mapper.ZoneMapper;
import co.ud.hashticket.pub.service.ZoneService;
import co.ud.ud.hashticket.dto.ZoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v.1/zone")
public class ZoneController {
    private final ZoneService zoneService;
    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }
    @GetMapping(value = "/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ZoneDto>> getByCategory(@PathVariable(value = "idCategory")Long idCategory){
        Set<ZoneEntity> entities = zoneService.getByCategory(idCategory);
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneMapper.INSTANCE.map(entities));
    }
}
