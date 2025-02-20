package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ManagementService extends ServiceCore {

    public ManagementService(UserBalanceTableRepository userBalanceTableRepository, UsersDataRepository usersDataRepository) {
        super(userBalanceTableRepository, usersDataRepository);
    }

    public String updateUserStatus(List<Integer> userIds, List<Boolean> status) {
        try {
            for (int i = 0; i < userIds.size(); i++) {
                usersDataRepository.setStatus(userIds.get(i), status.get(i));
            }
            return "Status updated successfully";
        } catch (Exception e) {
            return "Error updating status: " + e.getMessage();
        }
    }



}
