package sptech.school.nail_api.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "DTO padrão para representar erros retornados pela API.")
public class StandardErrorResponseDTO {

    @Schema(description = "Data e hora em que o erro ocorreu.", type = "string", format = "date-time", example = "2026-10-29T13:51:00")
    private LocalDateTime timestamp;

    @Schema(description = "Código HTTP do erro.", example = "400")
    private Integer status;

    @Schema(description = "Descrição padrão do erro HTTP.", example = "Bad Request")
    private String error;

    @Schema(description = "Mensagem detalhada explicando a causa do erro.", example = "O campo email possui um formato inválido.")
    private String message;

    @Schema(description = "Caminho da requisição que gerou o erro.", example = "/api/users")
    private String path;

    public StandardErrorResponseDTO() {
    }

    public StandardErrorResponseDTO(LocalDateTime timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}