package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.domain.UsersTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
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

    public List<UserDataDTO> getUsersData() {
        List<UserDataDTO> dtoList = new ArrayList<>();
        List<UsersTable> usersList = usersRepository.findAll();
        for (UsersTable user : usersList) {
            UserDataDTO userDataDTO = new UserDataDTO();
            userDataDTO.setUserId(user.getUser_id());
            userDataDTO.setLogin(user.getLogin());
            userDataDTO.setEmail(user.getEmail());
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }
}
