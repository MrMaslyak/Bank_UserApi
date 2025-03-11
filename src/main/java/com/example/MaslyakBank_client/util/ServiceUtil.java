package com.example.MaslyakBank_client.util;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceUtil {

    private final UserBalanceTableRepository userBalanceTableRepository;
    private final UsersDataRepository usersDataRepository;


    @Autowired
    public ServiceUtil(UserBalanceTableRepository userBalanceTableRepository, UsersDataRepository usersDataRepository) {
        this.userBalanceTableRepository = userBalanceTableRepository;
        this.usersDataRepository = usersDataRepository;
    }


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
