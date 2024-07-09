package com.mockproject.AuctionManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_information_account")
public class InformationAccountEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info_acc")
    private Long idInfor;

    @Column(name = "cart_number")
    private String cartNumber;

    @Column(name = "expiration")
    private String expiration;

    @Column(name = "cvv")
    private Integer cvv;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "state")
    private String state;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city_account")
    private String cityAccount;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;
}
