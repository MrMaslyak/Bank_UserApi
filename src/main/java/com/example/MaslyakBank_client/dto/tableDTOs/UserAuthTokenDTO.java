package com.example.MaslyakBank_client.dto.tableDTOs;


import com.example.MaslyakBank_client.domain.UserDataTable;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAuthTokenDTO {

    private UserDataTable user_id;
    private String token;
    private LocalDateTime created_at;


}
