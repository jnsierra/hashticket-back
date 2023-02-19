package co.ud.hashticket.rest.controller.impl;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/event")
public class EventController {

    @GetMapping("/")
    public String hello() {
        return "Este es el servicio de eventos";
    }

}