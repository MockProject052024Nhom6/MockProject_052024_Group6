package com.mockproject.AuctionManagement.entity;

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
@Table(name = "tbl_holidays")
public class HolidayEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_holiday")
    private Integer idHoliday;

    @Column(name = "holiday_name")
    private String holidayName;

    @Column(name = "holiday_date")
    @Temporal(TemporalType.DATE)
    private Date HolidayDate;

    @Column(name = "holiday_type")
    private String holidayType;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "holidayEntity")
    private Set<AuctionEntity> auctionEntities = new HashSet<>();

}
