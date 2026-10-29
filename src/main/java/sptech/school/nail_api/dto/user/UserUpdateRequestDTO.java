package sptech.school.nail_api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "UserUpdateRequest", description = "Dados necessários para atualizar o perfil de um usuário.")
public class UserUpdateRequestDTO {

    @NotBlank(message = "The name cannot be blank.")
    @Size(max = 100, message = "The name must contain at most 100 characters.")
    @Schema(
            description = "Nome do usuário.",
            example = "Maria Silva",
            minLength = 1,
            maxLength = 100,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotBlank(message = "The email cannot be blank.")
    @Email(message = "The email must be correctly formatted.")
    @Size(max = 255, message = "The email must contain at most 255 characters.")
    @Schema(
            description = "E-mail do usuário. Deve possuir um formato válido e não estar cadastrado para outro usuário.",
            example = "maria@email.com",
            format = "email",
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @Size(max = 20, message = "The phone must contain at most 20 characters.")
    @Schema(
            description = "Telefone de contato. Campo opcional.",
            example = "+5511999999999",
            maxLength = 20,
            nullable = true,
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String phone;

    public UserUpdateRequestDTO() {
    }

    public UserUpdateRequestDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}