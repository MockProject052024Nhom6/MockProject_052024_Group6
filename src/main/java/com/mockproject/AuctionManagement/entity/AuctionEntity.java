package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.AuctionStatus;
import com.mockproject.AuctionManagement.enums.TypeAuction;
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
@Table(name = "tbl_auction")
public class AuctionEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auction")
    private Long idAuction;

    @Column(name = "auction_name")
    private String auctionName;

    @Column(name = "address")
    private String address;

    @Column(name = "number_of_participants")
    private Integer numberOfParticipants;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "type_auction")
    @Enumerated(EnumType.STRING)
    private TypeAuction typeAuction;

    @Column(name = "auction_status")
    @Enumerated(EnumType.STRING)
    private AuctionStatus auctionStatus;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "auctionEntity")
    private Set<UserHasAuctionEntity> userHasAuctionEntities = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "id_auctioneer")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "auctionEntity")
    private Set<AuctionHasAssetEntity> auctionHasAssetEntities = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "id_holiday")
    private HolidayEntity holidayEntity;

}
