package com.List.ToDo.service;

import org.springframework.stereotype.Service;

import com.List.ToDo.dto.UserDTO;
import com.List.ToDo.entities.UserEntity;
import com.List.ToDo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO dto) {
        UserEntity user = new UserEntity(dto);
        userRepository.save(user);
        UserDTO userDto = new UserDTO(user.getName(), user.getEmail());
        return userDto;
    }

    public List<UserDTO> listUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> new UserDTO(user.getName(), user.getEmail())).toList();
        return userDTOs;
    }

    public UserDTO getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity user = userEntityOptional.get();
            return new UserDTO(user.getName(), user.getEmail());
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            userRepository.delete(userEntityOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
    }
}

