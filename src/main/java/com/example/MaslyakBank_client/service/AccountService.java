package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.domain.UserDataTable;
import com.example.MaslyakBank_client.dto.tableDTOs.UserBalanceDTO;
import com.example.MaslyakBank_client.dto.tableDTOs.UserDataDTO;
import com.example.MaslyakBank_client.mappers.UserDataMapper;
import com.example.MaslyakBank_client.repository.UserDataRepository;
import com.example.MaslyakBank_client.util.ServiceUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountService {

    private final UserDataRepository userDataRepository;
    private final ServiceUtil serviceUtil;
    private final UserDataMapper  userDataMapper;


    @Autowired
    public AccountService(
            UserDataRepository userDataRepository,
            ServiceUtil serviceUtil,
            UserDataMapper  userDataMapper
    ) {
        this.userDataRepository = userDataRepository;
        this.serviceUtil = serviceUtil;
        this.userDataMapper = userDataMapper;
    }

    @PostConstruct
    public void init() {
        log.warn("⚠️✅ TODO: ForI в методе getUsers должен заменить MapperClass", AccountService.class.getName() + ".java");
    }

    public List<UserBalanceDTO> getUserBalance(List<Integer> userIds) {
        return serviceUtil.getUserBalance(userIds);
    }

    public List<UserDataDTO> getUsers() {
        List<UserDataDTO> dtoList = new ArrayList<>();
        List<UserDataTable> usersList = userDataRepository.findAll();
        for (UserDataTable user : usersList) {//todo
           UserDataDTO userDataDTO = userDataMapper.toUserDataDTO(user);
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }



}
