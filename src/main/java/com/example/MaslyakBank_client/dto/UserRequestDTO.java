package com.example.MaslyakBank_client.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    private List<Integer> users_id;
    private List<String> logins;
    private String login;
    private String email;
    private int user_id;
    private boolean getStatus;

}
