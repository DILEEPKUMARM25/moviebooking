package com.example.moviebooking.user.repository;

import com.example.moviebooking.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<Object> findByEmail(@Email @NotBlank String email);
}
