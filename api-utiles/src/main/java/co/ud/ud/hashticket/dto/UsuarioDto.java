package co.ud.ud.hashticket.dto;

import co.ud.ud.hashticket.enumeration.USER_STATE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;
    private String correo;
    private String codigo;
    private String contrasena;
    private String nombre;
    private String cambioContra;
    private Integer intentos;
    private USER_STATE estado;
    private TipoUsuarioDto tipoUsuario;
}
