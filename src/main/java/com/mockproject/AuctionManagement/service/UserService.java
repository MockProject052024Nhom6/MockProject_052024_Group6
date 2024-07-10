package com.mockproject.AuctionManagement.service;

import com.mockproject.AuctionManagement.dto.UserDTO;
import com.mockproject.AuctionManagement.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<UserDTO> getUserWithRoles(Long idUser);

    Optional<UserDTO> updateUser( UserEntity updatedUser);

    String deleteUser(Long idUser);
}
