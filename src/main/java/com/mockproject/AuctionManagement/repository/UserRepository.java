package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
<<<<<<< HEAD
    @EntityGraph(attributePaths = {"userHasRoleEntities"})
    Optional<UserEntity> findById(Long idUser);
=======
  
>>>>>>> d0883dccd94685be32e6a66d603a943c0516fc95
    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
  
    Optional<UserEntity> findByEmail(String email);
  
    Optional<UserEntity> getUserEntityByIdUser(Long idUser);
}
