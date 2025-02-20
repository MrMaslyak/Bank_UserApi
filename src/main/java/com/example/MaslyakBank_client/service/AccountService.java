package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.domain.UsersDataTable;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService  extends ServiceCore {

    public AccountService (UserBalanceTableRepository userBalanceTableRepository, UsersDataRepository usersDataRepository) {
        super(userBalanceTableRepository, usersDataRepository);
    }

    public List<UserDataDTO> getUsers() {
        List<UserDataDTO> dtoList = new ArrayList<>();
        List<UsersDataTable> usersList = usersDataRepository.findAll();
        for (UsersDataTable user : usersList) {
            UserDataDTO userDataDTO = new UserDataDTO();
            userDataDTO.setUserId(user.getUser_id());
            userDataDTO.setLogin(user.getLogin());
            userDataDTO.setEmail(user.getEmail());
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }

}
