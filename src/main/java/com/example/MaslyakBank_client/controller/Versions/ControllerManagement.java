package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserRequestDTO;
import com.example.MaslyakBank_client.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/v2")
public class ControllerManagement {


    private final ManagementService accountManagement;

    @GetMapping("/user_balance")
    public List<UserDataBalanceDTO> getBalanceUser(@RequestHeader("user_id") List<Integer> userIds) {
        return accountManagement.getUserBalance(userIds);
    }

    @PostMapping("/update_status")
    public String updateUserStatus(@RequestBody UserRequestDTO userRequestDTO) {
        return accountManagement.updateUserStatus(userRequestDTO.getUsers_id(), userRequestDTO.getIsStatus());
    }
    @PostMapping("/update_status_2")
    public String updateUserStatusV2(@RequestBody List<UserRequestDTO> userRequestDTOList) {
        return accountManagement.updateUserStatusV2(userRequestDTOList);
    }


}
