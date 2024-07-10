package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.UserHasAuctionStatus;
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
@Table(name = "tbl_user_has_auction")
public class UserHasAuctionEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "deposits")
    private Double deposits;

    @Column(name = "deposits_date")
    private Date depositsDate;

    @Column(name = "user_has_auction_status")
    @Enumerated(EnumType.STRING)
    private UserHasAuctionStatus userHasAcutionStatus;

    @Column(name = "status")
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "id_auction")
    private AuctionEntity auctionEntity;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;
}
