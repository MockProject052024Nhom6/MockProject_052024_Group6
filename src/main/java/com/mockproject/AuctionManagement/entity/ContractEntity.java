package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_contract")
public class ContractEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private Integer idContract;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_date")
    @Temporal(TemporalType.DATE)
    private Date contractDte;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "status_contract")
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

    @Column(name = "status")
    private Integer endTime;

    @Column(name = "type_auction")
    private String typeAuction;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "contractEntity")
    private Set<TaxHasContractEntity> taxHasContractEntities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_auction_asset")
    private AuctionHasAssetEntity auctionHasAssetEntity;
}
