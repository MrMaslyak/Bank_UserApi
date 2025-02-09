package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.dto.BalanceDTO;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceMBank {


    private final UserBalanceRepository userBalanceRepository;


    public BalanceDTO getBalance(int userId) {
        Optional<String> userBalance = userBalanceRepository.findBalanceByUserId(userId);
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setUserId(userId);
        balanceDTO.setBalance(userBalance.orElse("User not regist in system"));
        return balanceDTO;
    }
}
