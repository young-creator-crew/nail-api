package sptech.school.nail_api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para atualizar o perfil de um usuário.")
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

    public UserUpdateRequestDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}