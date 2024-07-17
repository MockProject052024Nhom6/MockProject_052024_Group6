package com.mockproject.AuctionManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tbl_category_asset")
public class CategoryAssetEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cate_asset")
    private Integer idCateAsset;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryAssetEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<AssetEntity> assetEntities = new HashSet<>();
}
