package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.AssetStatus;
import com.mockproject.AuctionManagement.enums.TypeAuction;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbl_asset")
public class AssetEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asset")
    private Long idAsset;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "size")
    private String size;

    @Column(name = "estimated_value")
    private Double estimatedValue;

    @Column(name = "origin")
    private String origin;

    @Column(name = "property_status")
    private String propertyStatus;

    @Column(name = "type_auction")
    @Enumerated(EnumType.STRING)
    private TypeAuction typeAuction;

    @Column(name = "quantity")
    @Min(1)
    private Integer quantity;

    @Column(name = "status_asset")
    @Enumerated(EnumType.STRING)
    private AssetStatus assetStatus;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_warehouse")
    private WarehouseEntity warehouseEntity;

    @ManyToOne
    @JoinColumn(name = "id_cate_asset")
    private CategoryAssetEntity categoryAssetEntity;

    @OneToMany(mappedBy = "assetEntity")
    private Set<AssetMediaEntity> assetMediaEntities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_appraisers")
    private AppraisersEntity appraisersEntity;

    @ManyToOne
    @JoinColumn(name = "id_user_winner")
    private UserEntity userWinner;

    @ManyToOne
    @JoinColumn(name = "id_seller")
    private UserEntity seller;
}
