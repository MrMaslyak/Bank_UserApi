package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import com.example.MaslyakBank_client.repository.UsersRepository;
import org.springframework.stereotype.Service;


@Service
public class ServiceV2  extends BaseService {

    public ServiceV2(UserBalanceRepository userBalanceRepository, UsersRepository usersRepository) {
        super(userBalanceRepository, usersRepository);
    }

    public String updateUserDisabled(int userId, boolean disabled) {
        usersRepository.setDisabled(userId, disabled);
        return "User: " + userId + " disabled: " + disabled;
    }


}
