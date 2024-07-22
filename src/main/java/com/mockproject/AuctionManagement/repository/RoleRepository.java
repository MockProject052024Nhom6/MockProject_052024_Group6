package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.RoleEntity;
import com.mockproject.AuctionManagement.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByRoleName(UserRole roleName);
}
