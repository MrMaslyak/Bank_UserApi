package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.service.ServiceMBank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class BankControllerV2 {


    private final ServiceMBank serviceMBank;


    @GetMapping("/")
    public String home() {
        return "Home Page MBank";
    }



}
