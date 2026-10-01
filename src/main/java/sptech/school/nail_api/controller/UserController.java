package sptech.school.nail_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.user.UpdateRequestDTO;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.mapper.UserMapper;
import sptech.school.nail_api.model.User;
import sptech.school.nail_api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getProfile(@PathVariable Integer id) {
        User user = userService.get(id);
        return ResponseEntity.status(200).body(UserMapper.userToUserResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UpdateRequestDTO request, @PathVariable Integer id) {
        User response = userService.update(UserMapper.updateRequestToUser(request), id);
        return ResponseEntity.status(200).body(UserMapper.userToUserResponse(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(204).build();
    }

}