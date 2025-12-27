package com.example.moviebooking.user.service;

import com.example.moviebooking.user.dto.UserRequestDTO;
import com.example.moviebooking.user.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    UserResponseDTO updateUser(Integer id, UserRequestDTO dto);

    UserResponseDTO getUserById(Integer id);

    List<UserResponseDTO> getAllUsers();

    void deleteUser(Integer id);

    UserResponseDTO patchUser(Integer id, UserRequestDTO dto);
}
