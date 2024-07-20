package com.mockproject.AuctionManagement.dto;

import com.mockproject.AuctionManagement.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String username;
    private Date dateOfBirth;
    private String email;
    private String phone;
    private String anotherPhone;
    private String address;
    private String gender;
    private String country;
    private UserStatus userStatus;
    private String city;
    private String state;
    private String avatar;
    private String interest;
    private Integer credibility;
    private String identificationCard;
    private String demand;
    private Integer status;
    private Set<String> roles;


}