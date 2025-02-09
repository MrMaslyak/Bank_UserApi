package com.example.MaslyakBank_client.controller;

import com.example.MaslyakBank_client.dto.BalanceDTO;
import com.example.MaslyakBank_client.service.ServiceMBank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BankController {

    private final ServiceMBank serviceMBank;


    @GetMapping("/")
    public String home() {
        return "Home Page MBank";
    }

    @GetMapping("/money")
    public BalanceDTO getBalance(@RequestParam("user_id") int userId) {
        return serviceMBank.getBalance(userId);
    }
}
