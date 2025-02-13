package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import com.example.MaslyakBank_client.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public abstract class BaseService {

    protected final UserBalanceRepository userBalanceRepository;
    protected final UsersRepository usersRepository;

    public List<UserDataBalanceDTO> getUsersBalanceData(List<Integer> userIds) {
        List<UserDataBalanceDTO> dtoList = new ArrayList<>();
        for (int userId : userIds) {
            Optional<String> userBalance = userBalanceRepository.findBalanceByUserId(userId);
            Optional<String> userLogin = usersRepository.findLoginByUserId(userId);
            UserDataBalanceDTO userDataDTO = new UserDataBalanceDTO();
            userDataDTO.setUserId(userId);
            userDataDTO.setLogin(userLogin.orElse("User not regist in system"));
            userDataDTO.setBalance(userBalance.orElse("User not regist in system"));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }

}
