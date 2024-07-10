package com.mockproject.AuctionManagement.controller;

import com.mockproject.AuctionManagement.dto.UserDTO;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long idUser) {
        return ResponseEntity.of(userService.getUserWithRoles(idUser));
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserEntity userUpdate) {
        return ResponseEntity.of(userService.updateUser(userUpdate));
    }

    @DeleteMapping("/{idUser}")
    public String deleteUser(@PathVariable Long idUser) {
        return userService.deleteUser(idUser);
    }

}
