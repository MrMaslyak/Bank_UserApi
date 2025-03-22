package com.example.MaslyakBank_client.dto.tableDTOs;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserBalanceDTO {

    @Size(min = 4, max = 20, message = "Login must be between 4 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must contain only letters and numbers")
    private String login;
    private int userId;
    private String balance;


}
