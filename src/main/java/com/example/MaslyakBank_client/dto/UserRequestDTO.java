package com.example.MaslyakBank_client.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    private List<Integer> users_id;
    private List<String> login;
    private List<Boolean> isStatus;
}
