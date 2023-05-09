package co.ud.ud.hashticket.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class ExternalUserDto {
    @Email(message = "No es un correo")
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate birthDate;
    private String idNumber;
    @NotBlank
    private String address;
    @NotBlank
    private String mobileNumber;
}
