package com.example.MaslyakBank_client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/moneybank")
    public String moneybank() {
        return "moneybank";
    }

}