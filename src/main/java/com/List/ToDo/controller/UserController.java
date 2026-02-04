package com.List.ToDo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.List.ToDo.dto.UserDTO;
import com.List.ToDo.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto) {

        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("listUser")
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("listUser/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuário deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
