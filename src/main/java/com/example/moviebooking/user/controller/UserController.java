package com.example.moviebooking.user.controller;
import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.user.dto.UserRequestDTO;
import com.example.moviebooking.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ---------------- CREATE USER ----------------
    @PostMapping
    public ResponseEntity<CommonRespose> createUser(
            @Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(new CommonRespose(false,"User created successfully",userService.createUser(dto)));
       // return ;
    }

    // ---------------- UPDATE USER ----------------
    @PutMapping("/{id}")
    public ResponseEntity<CommonRespose> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(new CommonRespose(false,"User update successfully",userService.updateUser(id, dto)));


    }

    // ---------------- GET USER BY ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<CommonRespose> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(new CommonRespose(false,"Fetch user by id",userService.getUserById(id)));

       // return ;
    }

    // ---------------- GET ALL USERS ----------------
    @GetMapping
    public ResponseEntity<CommonRespose> getAllUsers() {
        return ResponseEntity.ok(new CommonRespose(false,"Fetch all user",userService.getAllUsers()));

       // return ;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonRespose> patchUser(
            @PathVariable Integer id,
            @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(new CommonRespose(false,"User update on particularfield ", userService.patchUser(id, dto)));

       // return;
    }


    // ---------------- DELETE USER ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonRespose> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new CommonRespose(false,"User update successfully",null));

       // return "User deleted successfully";
    }
}
