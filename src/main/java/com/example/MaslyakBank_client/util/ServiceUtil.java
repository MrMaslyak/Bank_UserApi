package com.example.MaslyakBank_client.util;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.mappers.UserBalanceMapper;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ServiceUtil {

    private final UserBalanceTableRepository userBalanceTableRepository;
    private final UsersDataRepository usersDataRepository;
    private final UserBalanceMapper userBalanceMapper;

    @Autowired
    public ServiceUtil(UserBalanceTableRepository userBalanceTableRepository, UsersDataRepository usersDataRepository, UserBalanceMapper userBalanceMapper) {
        this.userBalanceTableRepository = userBalanceTableRepository;
        this.usersDataRepository = usersDataRepository;
        this.userBalanceMapper = userBalanceMapper;
    }

    @PostConstruct
    public void init() {
        log.warn("⚠️✅ TODO: Перенести в MapperClass создание DTO для метода getUserBalance", ServiceUtil.class.getName() + ".java");
    }


    public List<UserDataBalanceDTO> getUserBalance(List<Integer> userIds) {
        List<UserDataBalanceDTO> dtoList = new ArrayList<>();
        for (int userId : userIds) {//todo
            UserDataBalanceDTO userDataDTO = userBalanceMapper.toUserDataBalanceDTO(userBalanceTableRepository.findById(userId).orElse(null));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }
}
