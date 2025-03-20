package com.example.MaslyakBank_client.dto;


import com.example.MaslyakBank_client.domain.UsersDataTable;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAuthTokenDTO {

    private UsersDataTable user_id;
    private String token;
    private LocalDateTime created_at;


}
