package sptech.school.nail_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.user.UserUpdateRequestDTO;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Consulta, atualização e desativação de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar usuário por ID", description = "Retorna o perfil de um usuário ativo pelo seu identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Identificador inválido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou inativo.", content = @Content)
    })
    public ResponseEntity<UserResponseDTO> getProfile(
            @Parameter(description = "Identificador do usuário.", example = "1", required = true)
            @PathVariable Integer id
    ) {
        return ResponseEntity.status(200).body(userService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza o nome, o e-mail e o telefone de um usuário ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou falha na validação.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou inativo.", content = @Content)
    })
    public ResponseEntity<UserResponseDTO> updateUser(
            @Parameter(description = "Identificador do usuário.", example = "1", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateRequestDTO request
    ) {
        return ResponseEntity.ok(userService.update(request, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar usuário", description = "Desativa logicamente o cadastro, preservando os registros relacionados.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário desativado."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.", content = @Content)
    })
    public ResponseEntity<Void> removeUser(
            @Parameter(description = "Identificador do usuário.", example = "1", required = true)
            @PathVariable Integer id
    ) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}