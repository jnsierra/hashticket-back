package co.ud.ud.hashticket.dto;

import co.ud.ud.hashticket.enumeration.TypeImages;
import lombok.Data;

@Data
public class EventImagesDto {
    private Long id;
    private String description;
    private String location;
    private TypeImages typeImages;
    private Long eventId;
    private String base64;
}
