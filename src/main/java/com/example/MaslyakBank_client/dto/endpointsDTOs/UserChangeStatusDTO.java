package com.example.MaslyakBank_client.dto.endpointsDTOs;


import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UserChangeStatusDTO {

    @Min(value = 1, message = "User ID must be greater than 0")
    private Integer user_id;
    private boolean getStatus;
}
