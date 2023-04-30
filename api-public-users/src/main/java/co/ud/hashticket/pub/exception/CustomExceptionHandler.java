package co.ud.hashticket.pub.exception;

import co.ud.ud.hashticket.dto.responses.GenericResponse;
import co.ud.ud.hashticket.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handlerBusinessException(BusinessException ex, WebRequest request){
        return ResponseEntity.ok(GenericResponse.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .type(ex.getType())
                .build());
    }
}
