package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
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

    @GetMapping("/setStatus")
    public String updateUserStatus(@RequestParam("user_id") int userId, @RequestParam("status") boolean  status ) {
        return accountManagement.updateUserStatus(userId, status);
    }

}
