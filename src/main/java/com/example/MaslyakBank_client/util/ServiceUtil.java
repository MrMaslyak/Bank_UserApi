package com.example.MaslyakBank_client.util;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import com.example.MaslyakBank_client.service.ManagementService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ServiceUtil {

    private final UserBalanceTableRepository userBalanceTableRepository;
    private final UsersDataRepository usersDataRepository;


    @Autowired
    public ServiceUtil(UserBalanceTableRepository userBalanceTableRepository, UsersDataRepository usersDataRepository) {
        this.userBalanceTableRepository = userBalanceTableRepository;
        this.usersDataRepository = usersDataRepository;
    }

    @PostConstruct
    public void init() {
        log.warn("⚠️⚠️ TODO: Перенести в MapperClass создание DTO для метода getUserBalance", ServiceUtil.class.getName() + ".java");
    }


    public List<UserDataBalanceDTO> getUserBalance(List<Integer> userIds) {
        List<UserDataBalanceDTO> dtoList = new ArrayList<>();
        for (int userId : userIds) {
            Optional<String> userBalance = userBalanceTableRepository.findBalanceByUserId(userId);
            Optional<String> userLogin = usersDataRepository.findLoginByUserId(userId);
            UserDataBalanceDTO userDataDTO = new UserDataBalanceDTO();//todo
            userDataDTO.setUserId(userId);
            userDataDTO.setLogin(userLogin.orElse("User not regist in system"));
            userDataDTO.setBalance(userBalance.orElse("User not regist in system"));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }
}
