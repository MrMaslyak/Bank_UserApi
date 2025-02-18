package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.domain.UsersTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import com.example.MaslyakBank_client.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceV2  extends BaseService {

    public ServiceV2(UserBalanceRepository userBalanceRepository, UsersRepository usersRepository) {
        super(userBalanceRepository, usersRepository);
    }

    public String updateUserDisabled(int userId, boolean disabled) {
        usersRepository.setDisabled(userId, disabled);
        return "User: " + userId + " disabled: " + disabled;
    }

    public List<UserDataBalanceDTO> getUsersByBody(List<Integer> userIds) {

        List<UsersTable> users = usersRepository.findAllById(userIds);
        List<UserDataBalanceDTO>  dtoList = new ArrayList<>();
        for (UsersTable user : users) {
            UserDataBalanceDTO userDataDTO = new UserDataBalanceDTO();
            userDataDTO.setUserId(user.getUser_id());
            userDataDTO.setLogin(user.getLogin());
            userDataDTO.setBalance(userBalanceRepository.findBalanceByUserId(user.getUser_id()).orElse("User not regist in system"));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }





}
