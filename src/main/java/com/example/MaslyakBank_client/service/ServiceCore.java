package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public abstract class ServiceCore {

    protected final UserBalanceTableRepository userBalanceTableRepository;
    protected final UsersDataRepository usersDataRepository;

    public List<UserDataBalanceDTO> getUserBalance(List<Integer> userIds) {
        List<UserDataBalanceDTO> dtoList = new ArrayList<>();
        for (int userId : userIds) {
            Optional<String> userBalance = userBalanceTableRepository.findBalanceByUserId(userId);
            Optional<String> userLogin = usersDataRepository.findLoginByUserId(userId);
            UserDataBalanceDTO userDataDTO = new UserDataBalanceDTO();
            userDataDTO.setUserId(userId);
            userDataDTO.setLogin(userLogin.orElse("User not regist in system"));
            userDataDTO.setBalance(userBalance.orElse("User not regist in system"));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }



}
