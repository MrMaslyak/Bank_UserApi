package com.example.MaslyakBank_client.controller.Versions;

import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.service.ServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BankControllerV1 {

    private final ServiceV1 serviceV1;


    @GetMapping("/")
    public String home() {
        return "Home Page MBank";
    }

    @GetMapping("/data")
    public List<UserDataBalanceDTO> getDataBalanceUsers(@RequestParam("user_id") List<Integer> userIds) {
        return serviceV1.getUsersBalanceData(userIds);
    }

    @GetMapping("/users")
    public List<UserDataDTO> getDataUsers() {
        return serviceV1.getUsersData();
    }



}
