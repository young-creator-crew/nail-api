package sptech.school.nail_api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdatePasswordRequestDTO {

    @NotBlank
    @Schema(
            description = "Senha Atual",
            example = "Senha123!",
            format = "password",
            minLength = 8,
            maxLength = 128,
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String senhaAtual;

    @NotBlank
    @Size(min = 8, max = 128, message = "The password must contain between 8 and 128 characters.")
    @Schema(
            description = "Senha nova, com no mínimo 8 e no máximo 128 caracteres.",
            example = "Senha123!",
            format = "password",
            minLength = 8,
            maxLength = 128,
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String senhaNova;

    public UserUpdatePasswordRequestDTO() {
    }

    public UserUpdatePasswordRequestDTO(String senhaAtual, String senhaNova) {
        this.senhaAtual = senhaAtual;
        this.senhaNova = senhaNova;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }
}
