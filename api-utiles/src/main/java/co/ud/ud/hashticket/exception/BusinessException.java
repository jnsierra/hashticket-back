package co.ud.ud.hashticket.exception;

import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import lombok.Getter;

public class BusinessException extends RuntimeException {
    @Getter
    private final Long code;
    @Getter
    private final TYPE_EXCEPTION type;
    public BusinessException(Long code, TYPE_EXCEPTION type, String message) {
        super(message);
        this.code = code;
        this.type = type;
    }
}
