package com.example.MaslyakBank_client.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    private List<Integer> userId;
    private List<String> login;
}
