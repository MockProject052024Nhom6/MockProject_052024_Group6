package com.mockproject.AuctionManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotBlank(message = "First name is not blank")
    private String firstName;

    @NotBlank(message = "Last name is not blank")
    private String lastName;

    @Email
    @NotBlank(message = "Email is not blank")
    String email;

    @NotBlank(message = "Password is not blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;
}
