//package com.example.moviebooking.securityconfig.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@AllArgsConstructor
//@Data
//public class AuthResponse {
//    private String token;
//}

package com.example.moviebooking.securityconfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String username;
    private String role;
    private int id;
}