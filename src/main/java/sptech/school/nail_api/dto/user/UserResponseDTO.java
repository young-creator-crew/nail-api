package sptech.school.nail_api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import sptech.school.nail_api.enums.UserType;

@Schema(name = "UserResponse", description = "Dados públicos retornados nas operações de usuário.")
public class UserResponseDTO {

    @Schema(description = "Identificador do usuário.", example = "1")
    private Integer id;

    @Schema(description = "E-mail cadastrado.", example = "maria@email.com", format = "email")
    private String email;

    @Schema(description = "Identificador do usuário.", example = "1")
    private String name;

    @Schema(description = "Telefone de contato; pode ser nulo.", example = "+5511999999999", nullable = true)
    private String phone;

    @Schema(description = "Tipo do usuário.", example = "CLIENTE")
    private UserType userType;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Integer id, String email, String name, String phone, UserType userType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}