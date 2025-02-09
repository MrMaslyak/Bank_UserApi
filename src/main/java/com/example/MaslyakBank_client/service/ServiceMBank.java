package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import com.example.MaslyakBank_client.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceMBank {


    private final UserBalanceRepository userBalanceRepository;
    private final UsersRepository usersRepository;


    public List<UserDataDTO> getUsersData(List<Integer> userIds) {

        List<UserDataDTO> dtoList = new ArrayList<>();
        for (int userId : userIds) {
            Optional<String> userBalance = userBalanceRepository.findBalanceByUserId(userId);
            Optional<String> userLogin = usersRepository.findLoginByUserId(userId);
            UserDataDTO userDataDTO = new UserDataDTO();
            userDataDTO.setUserId(userId);
            userDataDTO.setLogin(userLogin.orElse("User not regist in system"));
            userDataDTO.setBalance(userBalance.orElse("User not regist in system"));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }
}
