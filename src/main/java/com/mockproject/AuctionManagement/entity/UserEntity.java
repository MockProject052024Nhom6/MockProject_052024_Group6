package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "`tbl_user`")
public class UserEntity extends AbstractEntity{

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

    @OneToMany(mappedBy = "userEntity")
    private Set<UserHasRoleEntity> userHasRoleEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private Set<InformationAccountEntity> informationAccountEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private Set<UserHasNotificationEntity> userHasNotificationEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private Set<TransactionHistoryEntity> transactionHistoryEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private Set<UserHasAuctionEntity> userHasAuctionEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private Set<ContractEntity> contractEntities = new HashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private Set<BidEntity> bidEntities = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // Tên bảng liên kết
            joinColumns = @JoinColumn(name = "user_id"), // Tên cột trong bảng user_entity
            inverseJoinColumns = @JoinColumn(name = "id_role") // Tên cột trong bảng tbl_role
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
