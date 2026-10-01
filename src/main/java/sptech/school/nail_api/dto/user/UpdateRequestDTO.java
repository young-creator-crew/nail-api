package sptech.school.nail_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateRequestDTO {

    @NotBlank
    @Email(message = "The email cannot be null, empty, or contain only whitespace.")
    private String email;
    @NotBlank
    private String name;

    public UpdateRequestDTO() {
    }

    public UpdateRequestDTO(String email, String name) {
        this.email = email;
        this.name = name;
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
}
