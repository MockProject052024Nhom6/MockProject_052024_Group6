package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_fee")
public class FeeEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fee")
    private Integer idAuction;

    @Column(name = "fee_name")
    private String feeName;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_auction_asset")
    private AuctionHasAssetEntity auctionHasAssetEntity;
}
