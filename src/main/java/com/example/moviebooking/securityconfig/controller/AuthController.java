//package com.example.moviebooking.securityconfig.controller;
//
//
//import com.example.moviebooking.securityconfig.authprovider.JwtUtil;
//import com.example.moviebooking.securityconfig.dto.AuthRequest;
//import com.example.moviebooking.securityconfig.dto.AuthResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class
//AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//
//    public AuthController(AuthenticationManager authenticationManager,
//                          JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(
//            @RequestBody AuthRequest request) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword())
//        );
//
//        String token = jwtUtil.generateToken(request.getUsername());
//
//        return ResponseEntity.ok(new AuthResponse(token));
//    }
//}
//

package com.example.moviebooking.securityconfig.controller;

import com.example.moviebooking.securityconfig.authprovider.JwtUtil;
import com.example.moviebooking.securityconfig.dto.AuthRequest;
import com.example.moviebooking.securityconfig.dto.AuthResponse;
import com.example.moviebooking.user.entity.User;
import com.example.moviebooking.user.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {

        // Step 1: Authenticate
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),   // email
                        request.getPassword())
        );

        // Step 2: Fetch user from DB (FIXED)
        User user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 3: Generate token
        String token = jwtUtil.generateToken(user.getEmail());

        // Step 4: Get role
        String role = user.getRole().name();

        // Step 5: Return response
        AuthResponse response = new AuthResponse(
                token,
                user.getEmail(),   // username = email
                role,
                user.getId()
        );

        return ResponseEntity.ok(response);
    }
}