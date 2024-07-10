package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
  
    Optional<UserEntity> findByEmail(String email);
  
    Optional<UserEntity> getUserEntityByIdUser(Long idUser);
}
