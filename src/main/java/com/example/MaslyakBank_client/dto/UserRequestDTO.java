package com.example.MaslyakBank_client.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    @Min(value = 1, message = "User ID must be greater than 0")
    private Integer user_id;

    @Size(min = 4, max = 20, message = "Login must be between 4 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must contain only letters and numbers")
    private String login;

    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email must be at most 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    private List<Integer> users_id;
    private List<String> logins;

}
