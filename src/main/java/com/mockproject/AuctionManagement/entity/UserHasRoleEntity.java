package com.mockproject.AuctionManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user_has_role")
public class UserHasRoleEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "id_role")
    private RoleEntity roleEntity;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;
}
