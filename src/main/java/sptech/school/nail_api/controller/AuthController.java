package sptech.school.nail_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.auth.LoginRequestDTO;
import sptech.school.nail_api.dto.auth.RegisterRequestDTO;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.service.AuthService;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Operações de login e cadastro de usuários.")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Valida o e-mail e a senha de um usuário ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados enviados inválidos.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas ou usuário inativo.", content = @Content)
    })
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    @Operation(summary = "Cadastrar usuário", description = "Cria uma conta de usuário após validar os dados enviados.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados enviados inválidos.", content = @Content),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado.", content = @Content)
    })
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.status(201).body(authService.register(request));
    }
}