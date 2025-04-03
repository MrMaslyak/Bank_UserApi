package com.example.MaslyakBank_client.dto.errorsDTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationErrorDTO {

    private Integer errorCode;
    private String errorMessage;
    private Map <String, String> errorDetails;

}
