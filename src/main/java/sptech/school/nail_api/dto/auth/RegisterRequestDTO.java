package sptech.school.nail_api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "RegisterRequest", description = "Dados necessários para cadastrar um novo usuário.")
public class RegisterRequestDTO {

    @NotBlank(message = "The name cannot be blank.")
    @Size(max = 100, message = "The name must contain at most 100 characters.")
    @Schema(
            description = "Nome do usuário.",
            example = "Maria Silva",
            maxLength = 100,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotBlank(message = "The email cannot be blank.")
    @Email(message = "The email must be correctly formatted.")
    @Size(max = 255, message = "The email must contain at most 255 characters.")
    @Schema(
            description = "E-mail que será utilizado para acessar a aplicação.",
            example = "maria@email.com",
            format = "email",
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @NotBlank(message = "The password cannot be blank.")
    @Size(min = 8, max = 128, message = "The password must contain between 8 and 128 characters.")
    @Schema(
            description = "Senha original, com no mínimo 8 e no máximo 128 caracteres.",
            example = "Senha123!",
            format = "password",
            minLength = 8,
            maxLength = 128,
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;

    @Size(max = 20, message = "The phone must contain at most 20 characters.")
    @Schema(
            description = "Telefone de contato. Campo opcional.",
            example = "+5511999999999",
            maxLength = 20,
            nullable = true,
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String phone;

    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}