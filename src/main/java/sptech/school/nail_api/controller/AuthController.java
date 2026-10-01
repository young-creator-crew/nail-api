package sptech.school.nail_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.auth.LoginRequestDTO;
import sptech.school.nail_api.dto.auth.RegisterRequestDTO;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.mapper.UserMapper;
import sptech.school.nail_api.model.User;
import sptech.school.nail_api.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.status(200).body(UserMapper.userToUserResponse(authService.login(request.getEmail(), request.getPassword())));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        User user = UserMapper.registerRequestToUser(request);
        return ResponseEntity.status(201).body(UserMapper.userToUserResponse(authService.register(user)));
    }

}