package com.example.MaslyakBank_client.util;


import com.example.MaslyakBank_client.dto.tablesDTOs.UserBalanceDTO;
import com.example.MaslyakBank_client.mappers.UserBalanceMapper;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import com.example.MaslyakBank_client.repository.UserDataRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ServiceUtil {

    private final UserBalanceRepository userBalanceRepository;
    private final UserDataRepository userDataRepository;
    private final UserBalanceMapper userBalanceMapper;

    @Autowired
    public ServiceUtil(UserBalanceRepository userBalanceRepository, UserDataRepository userDataRepository, UserBalanceMapper userBalanceMapper) {
        this.userBalanceRepository = userBalanceRepository;
        this.userDataRepository = userDataRepository;
        this.userBalanceMapper = userBalanceMapper;
    }

    @PostConstruct
    public void init() {
        log.warn("✅✅ TODO: Перенести в MapperClass создание DTO для метода getUserBalance", ServiceUtil.class.getName() + ".java");
    }


    public List<UserBalanceDTO> getUserBalance(List<Integer> userIds) {
        List<UserBalanceDTO> dtoList = new ArrayList<>();
        for (int userId : userIds) {//todo
            UserBalanceDTO userDataDTO = userBalanceMapper.toUserDataBalanceDTO(userBalanceRepository.findById(userId).orElse(null));
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }
}
