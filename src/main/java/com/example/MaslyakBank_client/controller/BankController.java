package com.example.MaslyakBank_client.controller;

import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.service.ServiceMBank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BankController {

    private final ServiceMBank serviceMBank;


    @GetMapping("/")
    public String home() {
        return "Home Page MBank";
    }

    @GetMapping("/data")
    public List<UserDataDTO> getDataUsers(@RequestParam("user_id") List<Integer> userIds) {
        return serviceMBank.getUsersData(userIds);
    }
}
