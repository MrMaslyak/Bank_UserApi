package com.example.MaslyakBank_client.controller;

import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BankController {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @GetMapping("/")
    public String home() {
        return "Home Page MBank";
    }

    @GetMapping("/money")
    public String getBalance(@RequestParam("user_id") int userId) {
        Optional<String> userBalance = userBalanceRepository.findBalanceByUserId(userId);


        if (userBalance.get() != null) {
            return "balance user: " + userBalance.get();
        } else {
            return "User not found";
        }

    }
}
