package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.domain.UsersTable;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import com.example.MaslyakBank_client.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceV1 extends BaseService {



    public ServiceV1(UserBalanceRepository userBalanceRepository, UsersRepository usersRepository) {
        super(userBalanceRepository, usersRepository);
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
