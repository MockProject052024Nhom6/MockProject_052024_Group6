package com.mockproject.AuctionManagement.entity;

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
<<<<<<< HEAD
public class RoleEntity extends AbstractEntity {
=======
public class RoleEntity extends AbstractEntity{
>>>>>>> 493b32c857be102df05a470798999e6143403954

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "roleEntity")
    private Set<UserHasRoleEntity> userHasRoleEntities = new HashSet<>();
}
