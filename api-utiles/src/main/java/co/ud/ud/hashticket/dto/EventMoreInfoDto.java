package co.ud.ud.hashticket.dto;

import co.ud.ud.hashticket.enumeration.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventMoreInfoDto {

    private Long id;
    private String name;
    private String place;
    private LocalDate date;
    private String time;
    private int minimumAge;
    private String responsible;
    private String nit;
    private String address;
    private String cityName;
    private String departmentName;
    private String countyName;
    private EventStatus eventStatus;
    private String categoryEventName;
    private List<PresentationDto> presentation;
    private List<ConfigEventDto> configEvents;
}
