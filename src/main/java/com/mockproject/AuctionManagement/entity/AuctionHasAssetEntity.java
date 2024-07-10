package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.AuctionResults;
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
@Table(name = "tbl_auction_has_asset")
public class AuctionHasAssetEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auction_asset")
    private Integer idAuctionAsset;

    @Column(name = "starting_price")
    private Double startingPrice;

    @Column(name = "present_price")
    private Double presentPrice;

    @Column(name = "auction_results")
    @Enumerated(EnumType.STRING)
    private AuctionResults auctionResults;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_auction")
    private AuctionEntity auctionEntity;

    @ManyToOne
    @JoinColumn(name = "id_asset")
    private AssetEntity assetEntity;

    @OneToMany(mappedBy = "auctionHasAssetEntity")
    private Set<ContractEntity> contractEntities = new HashSet<>();

    @OneToMany(mappedBy = "auctionHasAssetEntity")
    private Set<BidEntity> bidEntities = new HashSet<>();

    @OneToMany(mappedBy = "auctionHasAssetEntity")
    private Set<FeeEntity> feeEntities = new HashSet<>();
}
