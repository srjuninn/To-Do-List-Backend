package com.List.ToDo.service;

import org.springframework.stereotype.Service;

import com.List.ToDo.dto.UserDTO;
import com.List.ToDo.entities.UserEntity;
import com.List.ToDo.repositories.UserRepository;

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
}
