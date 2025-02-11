package com.example.MaslyakBank_client.dto;


import lombok.Data;

@Data
public class UserDataBalanceDTO {
    private int userId;
    private String login;
    private String balance;
}
