package co.ud.ud.hashticket.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GenericResponse<T> {
    private Long code;
    private String type;
    private String message;
    private T dataObject;
    private Set<T> data;
}
