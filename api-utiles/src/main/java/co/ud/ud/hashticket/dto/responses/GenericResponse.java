package co.ud.ud.hashticket.dto.responses;

import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GenericResponse<T> {
    private Long code;
    private TYPE_EXCEPTION type;
    private String message;
    private T dataObject;
    private Set<T> data;
}
