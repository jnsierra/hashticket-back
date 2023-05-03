package co.ud.ud.hashticket.dto;

import co.ud.ud.hashticket.enumeration.USER_STATE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private Long id;
    @Email(message = "invalid email")
    private String email;
    private String code;
    private String password;
    private String name;
    private String changePassword;
    private String attempts;
    private USER_STATE state;
}