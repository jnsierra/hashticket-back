package co.ud.ud.hashticket.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDto {

    private Long id;
    private String place;
    private LocalDate date;
    private String time;
    private String category;
    private int minimumAge;
    private String responsible;
    private String nit;
    private String address;
    private String openingDoors;
    private Long cityCode;
    private Long departmentCode;
}
