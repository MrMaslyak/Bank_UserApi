package com.example.MaslyakBank_client.dto.errorsDTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericErrorDTO {

    private Integer errorCode;
    private String errorMessage;
}
