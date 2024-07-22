package com.mockproject.AuctionManagement.entity;


import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.mockproject.AuctionManagement.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser")
public class UserEntity extends AbstractEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "phone", unique = true)
    @Pattern(regexp = "^0[0-9]{9}$")
    private String phone;

    @Column(name = "another_phone")
    private String anotherPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "country")
    private String country;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "interest")
    private String interest;

    @Column(name = "credibility")
    private Integer credibility;

    @Column(name = "identification_card")
    private String identificationCard;

    @Column(name = "demand")
    private String demand;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserHasRoleEntity> userHasRoleEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InformationAccountEntity> informationAccountEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserHasNotificationEntity> userHasNotificationEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<TransactionHistoryEntity> transactionHistoryEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserHasAuctionEntity> userHasAuctionEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ContractEntity> contractEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<BidEntity> bidEntities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userHasRoleEntities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleEntity().getRoleName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
