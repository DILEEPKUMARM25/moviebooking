package com.example.moviebooking.user.service.imp;

import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.user.dto.UserRequestDTO;
import com.example.moviebooking.user.dto.UserResponseDTO;
import com.example.moviebooking.user.entity.User;
import com.example.moviebooking.user.repository.UserRepository;
import com.example.moviebooking.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    // ================= CREATE USER =================
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {

        // optional: check duplicate email
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new DataNotFoundException("Email already exists");
                });

        User user = mapToEntity(dto);

        // default active true (safe default)
        if (!dto.getActive()) {
            user.setActive(true);
        }

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    // ================= UPDATE USER =================
    @Override
    public UserResponseDTO updateUser(Integer id, UserRequestDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setPhoneNo(dto.getPhoneNo());
        user.setActive(dto.getActive());

        User updatedUser = userRepository.save(user);
        return mapToResponse(updatedUser);
    }

    // ================= GET USER BY ID =================
    @Override
    public UserResponseDTO getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    // ================= GET ALL USERS =================
    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }



    // ================= DELETE USER =================
    @Override
    public void deleteUser(Integer id) {

        if (!userRepository.existsById(id)) {
            throw new DataNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }


        @Override
        public UserResponseDTO patchUser(Integer id, UserRequestDTO dto) {

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));

            if (dto.getEmail() != null) {
                user.setEmail(dto.getEmail());
            }

            if (dto.getPassword() != null) {
                user.setPassword(dto.getPassword());
            }

            if (dto.getRole() != null) {
                user.setRole(dto.getRole());
            }

            if (dto.getPhoneNo() != null) {
                user.setPhoneNo(dto.getPhoneNo());
            }

            if (dto.getActive() != null) {
                user.setActive(dto.getActive());
            }

            User updatedUser = userRepository.save(user);
            return mapToResponse(updatedUser);
        }



    // ================= MAPPING METHODS =================

    private User mapToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        //user.setPassword(dto.getPassword()); // later encrypt
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setPhoneNo(dto.getPhoneNo());
        user.setActive(dto.getActive());
        return user;
    }

    private UserResponseDTO mapToResponse(User user) {
        return new UserResponseDTO(
                user.getName(),
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getPhoneNo(),
                user.isActive()
        );
    }
}
