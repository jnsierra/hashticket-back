package co.ud.ud.hashticket.dto;

import lombok.Data;

@Data
public class ZoneDto {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
}
