package com.example.MaslyakBank_client.controller.Versions;

import com.example.MaslyakBank_client.dto.tableDTOs.UserBalanceDTO;
import com.example.MaslyakBank_client.dto.tableDTOs.UserDataDTO;
import com.example.MaslyakBank_client.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/v1")
public class AccountController {

    private final AccountService account;



    @GetMapping("/user_balance")
    public List<UserBalanceDTO> getUserBalance(@RequestParam("user_id") List<Integer> userIds) {
        return account.getUserBalance(userIds);

    }

    @GetMapping("/users")
    public List<UserDataDTO> getUsers() {
        return account.getUsers();
    }

}
