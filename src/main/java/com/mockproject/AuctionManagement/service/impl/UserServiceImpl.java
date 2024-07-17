package com.mockproject.AuctionManagement.service.impl;

import com.mockproject.AuctionManagement.dto.UserDTO;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.repository.UserRepository;
import com.mockproject.AuctionManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserWithRoles(Long idUser) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(idUser);
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            Set<String> roles = userEntity.getUserHasRoleEntities()
                    .stream()
                    .map(userHasRole -> userHasRole.getRoleEntity().getRoleName().name())
                    .collect(Collectors.toSet());
            userDTO.setRoles(roles);
            return Optional.of(userDTO);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UserDTO> updateUser( UserEntity updatedUser) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(updatedUser.getIdUser());
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            if (updatedUser.getFirstName() != null) {
                userEntity.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null) {
                userEntity.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getUsername() != null) {
                userEntity.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getDateOfBirth() != null) {
                userEntity.setDateOfBirth(updatedUser.getDateOfBirth());
            }
            if (updatedUser.getEmail() != null) {
                userEntity.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPhone() != null) {
                userEntity.setPhone(updatedUser.getPhone());
            }
            if (updatedUser.getAnotherPhone() != null) {
                userEntity.setAnotherPhone(updatedUser.getAnotherPhone());
            }
            if (updatedUser.getAddress() != null) {
                userEntity.setAddress(updatedUser.getAddress());
            }
            if (updatedUser.getGender() != null) {
                userEntity.setGender(updatedUser.getGender());
            }
            if (updatedUser.getCountry() != null) {
                userEntity.setCountry(updatedUser.getCountry());
            }
            if (updatedUser.getUserStatus() != null) {
                userEntity.setUserStatus(updatedUser.getUserStatus());
            }
            if (updatedUser.getCity() != null) {
                userEntity.setCity(updatedUser.getCity());
            }
            if (updatedUser.getState() != null) {
                userEntity.setState(updatedUser.getState());
            }
            if (updatedUser.getAvatar() != null) {
                userEntity.setAvatar(updatedUser.getAvatar());
            }
            if (updatedUser.getInterest() != null) {
                userEntity.setInterest(updatedUser.getInterest());
            }
            if (updatedUser.getCredibility() != null) {
                userEntity.setCredibility(updatedUser.getCredibility());
            }
            if (updatedUser.getIdentificationCard() != null) {
                userEntity.setIdentificationCard(updatedUser.getIdentificationCard());
            }
            if (updatedUser.getDemand() != null) {
                userEntity.setDemand(updatedUser.getDemand());
            }
            if (updatedUser.getStatus() != null) {
                userEntity.setStatus(updatedUser.getStatus());
            }
            userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            Set<String> roles = userEntity.getUserHasRoleEntities()
                    .stream()
                    .map(userHasRole -> userHasRole.getRoleEntity().getRoleName().name())
                    .collect(Collectors.toSet());
            userDTO.setRoles(roles);
            return Optional.of(userDTO);
        }
        return Optional.empty();
    }

    @Override
    public String deleteUser(Long idUser) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(idUser);
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setStatus(0);
            userRepository.save(userEntity);
            return "User deleted successfully";
        }
        return "User not found";
    }
}
