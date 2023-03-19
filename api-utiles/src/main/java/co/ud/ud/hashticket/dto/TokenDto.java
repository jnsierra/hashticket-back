package co.ud.ud.hashticket.dto;

import co.ud.ud.hashticket.enumeration.LOGIN_ACTION;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String token;
    private Integer time;
    private String mensaje;
    private LOGIN_ACTION loginAction;
}
