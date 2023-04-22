package co.ud.ud.hashticket.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {
    @Getter
    private Long code;
    @Getter
    private String type;
    public BusinessException(Long code, String type, String message) {
        super(message);
        this.code = code;
        this.type = type;
    }
}
