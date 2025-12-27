package com.example.moviebooking.user.dto;



import com.example.moviebooking.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private String name;
    private Integer id;
    private String email;
    private UserRole role;
    private String phoneNo;
    private boolean active;
}

