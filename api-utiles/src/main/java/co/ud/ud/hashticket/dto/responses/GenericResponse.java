package co.ud.ud.hashticket.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {
    private Long code;
    private String type;
    private String message;
}
