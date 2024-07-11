package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.UserHasRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHasRoleRepository extends JpaRepository<UserHasRoleEntity, Integer> {
}
