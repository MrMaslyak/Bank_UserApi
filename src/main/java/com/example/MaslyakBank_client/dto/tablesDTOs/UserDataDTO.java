package com.example.MaslyakBank_client.dto.tablesDTOs;


import com.example.MaslyakBank_client.validator.annotations.Password;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDataDTO {

    @Min(value = 1, message = "User ID must be greater than 0")
    private Integer user_id;

    @Size(min = 4, max = 20, message = "Login must be between 4 and 20 characters")
    @NotEmpty(message = "Login cannot be null")
    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must contain only letters and numbers")
    private String login;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 50, message = "Email must be at most 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;


    @NotEmpty(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Password( message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character, 20 < symbols > 8")
    @JsonIgnore
    private String password;

    private boolean status;


}
