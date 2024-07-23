package com.mockproject.AuctionManagement.controller;

import com.mockproject.AuctionManagement.dto.UserDTO;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get user by id",
            description = "Get user by id"
    )
    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long idUser) {
        return ResponseEntity.of(userService.getUserWithRoles(idUser));
    }

    @Operation(
            summary = "Change user information",
            description = "Change user information"
    )
    @PutMapping("/{idUser}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserEntity userUpdate) {
        return ResponseEntity.of(userService.updateUser(userUpdate));
    }

    @Operation(
            summary = "Delete user",
            description = "Delete user"
    )
    @DeleteMapping("/{idUser}")
    public String deleteUser(@PathVariable Long idUser) {
        return userService.deleteUser(idUser);
    }

}
