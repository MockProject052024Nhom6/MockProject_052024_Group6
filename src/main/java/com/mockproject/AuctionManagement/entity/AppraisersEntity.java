package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.AppraisersStatus;
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
@Table(name = "tbl_appraisers")
public class AppraisersEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appraisers")
    private Integer idAppraisers;

    @Column(name = "experiences")
    private Integer experiences;

    @Column(name = "specialized")
    private String specialized;

    @Column(name = "status_appraisers")
    @Enumerated(EnumType.STRING)
    private AppraisersStatus statusAppraisers;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "appraisersEntity")
    private Set<AssetEntity> assetEntities = new HashSet<>();

    @OneToOne()
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;
}
