package com.example.MaslyakBank_client.dto.endpointsDTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserChangeEmailDTO {

    @Min(value = 1, message = "User ID must be greater than 0")
    private Integer user_id;

    @Size(max = 50, message = "Email must be at most 50 characters")
    @NotEmpty(message = "Email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
}
