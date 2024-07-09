package com.mockproject.AuctionManagement.service;

import com.mockproject.AuctionManagement.dto.UserDTO;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserWithRoles(Long idUser) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(idUser);
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            Set<String> roles = userEntity.getUserHasRoleEntities()
                    .stream()
                    .map(userHasRole -> userHasRole.getRoleEntity().getRoleName())
                    .collect(Collectors.toSet());
            userDTO.setRoles(roles);
            return Optional.of(userDTO);
        }
        return Optional.empty();
    }
}
