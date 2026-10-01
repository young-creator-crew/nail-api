package sptech.school.nail_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sptech.school.nail_api.dto.exception.StandardErrorResponseDTO;
import sptech.school.nail_api.exception.auth.InvalidCredentialsException;
import sptech.school.nail_api.exception.database.DataBaseAccessException;
import sptech.school.nail_api.exception.user.UserAlreadyExistsException;
import sptech.school.nail_api.exception.user.UserNotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataBaseAccessException.class)
    public ResponseEntity<StandardErrorResponseDTO> handleDataBaseAccess(DataBaseAccessException exception, HttpServletRequest request) {
        exception.printStackTrace();

        StandardErrorResponseDTO error = new StandardErrorResponseDTO(
                LocalDateTime.now(ZoneId.of("America/Sao_Paulo")),
                500,
                "Internal Server Error",
                "Internal error communicating with the database",
                request.getRequestURI()
        );

        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<StandardErrorResponseDTO> handleInvalidCredentials(InvalidCredentialsException exception, HttpServletRequest request) {
        exception.printStackTrace();

        StandardErrorResponseDTO error = new StandardErrorResponseDTO(
                LocalDateTime.now(ZoneId.of("America/Sao_Paulo")),
                401,
                "Unauthorized",
                "Request with invalid credentials",
                request.getRequestURI()
        );

        return ResponseEntity.status(401).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardErrorResponseDTO> handleUserAlreadyExists(UserAlreadyExistsException exception, HttpServletRequest request) {
        exception.printStackTrace();

        StandardErrorResponseDTO error = new StandardErrorResponseDTO(
                LocalDateTime.now(ZoneId.of("America/Sao_Paulo")),
                409,
                "Conflict",
                "User already exists in the database",
                request.getRequestURI()
        );

        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardErrorResponseDTO> handleUserNotFound(UserNotFoundException exception, HttpServletRequest request) {
        exception.printStackTrace();

        StandardErrorResponseDTO error = new StandardErrorResponseDTO(
                LocalDateTime.now(ZoneId.of("America/Sao_Paulo")),
                404,
                "Not Found",
                "User not found in the database",
                request.getRequestURI()
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponseDTO> handleValidationErrors(MethodArgumentNotValidException exception, HttpServletRequest request) {

        StandardErrorResponseDTO error = new StandardErrorResponseDTO(
                LocalDateTime.now(ZoneId.of("America/Sao_Paulo")),
                400,
                "Bad Request",
                "Invalid data, check the fields",
                request.getRequestURI()
        );

        return ResponseEntity.status(400).body(error);
    }

    public ResponseEntity<StandardErrorResponseDTO> handleGenericException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();

        StandardErrorResponseDTO error = new StandardErrorResponseDTO(
                LocalDateTime.now(ZoneId.of("America/Sao_Paulo")),
                500,
                "Internal Server Error",
                "An unexpected error occurred on the server",
                request.getRequestURI()
        );

        return ResponseEntity.status(500).body(error);
    }

}