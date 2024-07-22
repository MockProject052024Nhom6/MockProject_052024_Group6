package com.mockproject.AuctionManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotBlank(message = "First name is not blank")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "First name cannot contain special characters")
    private String firstName;

    @NotBlank(message = "Last name is not blank")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Last name cannot contain special characters")
    private String lastName;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$", message = "must be a well-formed email address")
    @NotBlank(message = "Email is not blank")
    private String email;

    @NotBlank(message = "Password is not blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{8,}", message = "Password must contain uppercase letters, lowercase letters, numbers and special characters")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
