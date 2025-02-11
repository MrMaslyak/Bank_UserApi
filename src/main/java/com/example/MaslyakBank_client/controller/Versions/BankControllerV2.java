package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.service.ServiceMBank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class BankControllerV2 {


    private final ServiceMBank serviceMBank;


    @GetMapping("/")
    public String home() {
        return "Home Page MBank";
    }


    @GetMapping("/data")
    public List<UserDataBalanceDTO> getDataBalanceUsers(@RequestHeader("users_id") List<Integer> userIds) {
        return serviceMBank.getUsersBalanceData(userIds);
    }

}
