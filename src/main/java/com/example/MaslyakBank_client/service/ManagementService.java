package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import org.springframework.stereotype.Service;


@Service
public class ManagementService extends ServiceCore {

    public ManagementService(UserBalanceTableRepository userBalanceTableRepository, UsersDataRepository usersDataRepository) {
        super(userBalanceTableRepository, usersDataRepository);
    }

    public String updateUserStatus(int userId, boolean status) {
        usersDataRepository.setStatus(userId, status);
        return "User: " + userId + " Status: " + status;
    }



}
