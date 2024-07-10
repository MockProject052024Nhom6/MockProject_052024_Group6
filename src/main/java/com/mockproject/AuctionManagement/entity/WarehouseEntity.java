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
@Table(name = "tbl_warehouse")
public class WarehouseEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_warehouse")
    private Integer idWarehouse;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "address_warehouse")
    private String addressWarehouse;

    @Column(name = "asset_placement")
    private String assetPlacement;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "warehouseEntity")
    private Set<AssetEntity> assetEntities = new HashSet<>();
}
