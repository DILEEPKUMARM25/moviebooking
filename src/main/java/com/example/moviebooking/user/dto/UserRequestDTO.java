package com.example.moviebooking.user.dto;


import com.example.moviebooking.user.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDTO {

    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private UserRole role;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number"
    )
    private String phoneNo;

    private Boolean active;
}
