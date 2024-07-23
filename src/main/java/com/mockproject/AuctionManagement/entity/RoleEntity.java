package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_role")

public class RoleEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "roleEntity")
    private Set<UserHasRoleEntity> userHasRoleEntities = new HashSet<>();
}
