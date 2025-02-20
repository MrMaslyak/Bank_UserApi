package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.service.AccountManagementService ;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class ControllerManagement {


    private final AccountManagementService  accountManagement;

    @GetMapping("/user_balance")
    public List<UserDataBalanceDTO> getBalanceUser(@RequestHeader("users_id") List<Integer> userIds) {
        return accountManagement.getUserBalance(userIds);
    }

    @GetMapping("/setDisabled")
    public String updateUserDisabled(@RequestParam("user_id") int userId, @RequestParam("disabled") boolean  disabled ) {
        return accountManagement.updateUserDisabled(userId, disabled);
    }

}
