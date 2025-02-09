package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceMBank {


    private final UserBalanceRepository userBalanceRepository;


    public List<UserDataDTO> getBalance(List<Integer> userIds) {

        List<UserDataDTO> dtoList = new ArrayList<>();
        for (int userId : userIds) {
            Optional<String> userBalance = userBalanceRepository.findBalanceByUserId(userId);
            UserDataDTO userDataDTO = new UserDataDTO();
            userDataDTO.setUserId(userId);
            userDataDTO.setBalance(userBalance.orElse("User not regist in system"));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }
}
