package sptech.school.nail_api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Credenciais utilizadas para autenticar um usuário.")
public class LoginRequestDTO {

    @NotBlank(message = "The email cannot be null, empty, or contain only whitespace.")
    @Email(message = "The email must be correctly formatted.")
    @Size(max = 255, message = "The email must contain at most 255 characters.")
    @Schema(
            description = "E-mail cadastrado na aplicação.",
            example = "maria@email.com",
            format = "email",
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @NotBlank(message = "The password cannot be null, empty, or contain only whitespace.")
    @Size(max = 128, message = "The password must contain at most 128 characters.")
    @Schema(
            description = "Senha original do usuário. Será validada com o hash armazenado.",
            example = "Senha123!",
            format = "password",
            maxLength = 128,
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}