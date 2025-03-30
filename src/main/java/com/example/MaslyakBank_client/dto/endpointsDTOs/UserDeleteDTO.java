package com.example.MaslyakBank_client.dto.endpointsDTOs;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDeleteDTO {


    @NotEmpty(message = "Users ID cannot be null")
    private List<Integer> users_id;

    private String message;

}
