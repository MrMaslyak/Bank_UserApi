package com.example.MaslyakBank_client;

import com.example.MaslyakBank_client.domain.UserBalanceTable;
import com.example.MaslyakBank_client.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/money/{userId}")
    public String getBalance(@PathVariable Long userId, Model model) {
        UserBalanceTable user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            model.addAttribute("balance", user.getBalance());
        } else {
            model.addAttribute("balance", "Пользователь не найден");
        }

        return "money";
    }


}