package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.domain.UsersDataTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import com.example.MaslyakBank_client.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final UsersDataRepository usersDataRepository;
    private final ServiceUtil serviceUtil;


    @Autowired
    public AccountService(
            UsersDataRepository usersDataRepository,
            ServiceUtil serviceUtil
    ) {
        this.usersDataRepository = usersDataRepository;
        this.serviceUtil = serviceUtil;
    }


    public List<UserDataBalanceDTO> getUserBalance(List<Integer> userIds) {
        return serviceUtil.getUserBalance(userIds);
    }

    public List<UserDataDTO> getUsers() {
        List<UserDataDTO> dtoList = new ArrayList<>();
        List<UsersDataTable> usersList = usersDataRepository.findAll();
        for (UsersDataTable user : usersList) { //todo должен делать специальный маппер - один вид класса перевестись в другой
            UserDataDTO userDataDTO = new UserDataDTO();
            userDataDTO.setUser_id(user.getId());
            userDataDTO.setLogin(user.getLogin());
            userDataDTO.setEmail(user.getEmail());
            userDataDTO.setPassword(user.getPassword());
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }



}
