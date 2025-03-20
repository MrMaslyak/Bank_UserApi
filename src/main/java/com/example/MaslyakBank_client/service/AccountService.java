package com.example.MaslyakBank_client.service;


import com.example.MaslyakBank_client.controller.Versions.ControllerManagement;
import com.example.MaslyakBank_client.domain.UsersDataTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.mappers.UserDataMapper;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
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

    private final UsersDataRepository usersDataRepository;
    private final ServiceUtil serviceUtil;
    private final UserDataMapper  userDataMapper;


    @Autowired
    public AccountService(
            UsersDataRepository usersDataRepository,
            ServiceUtil serviceUtil,
            UserDataMapper  userDataMapper
    ) {
        this.usersDataRepository = usersDataRepository;
        this.serviceUtil = serviceUtil;
        this.userDataMapper = userDataMapper;
    }

    @PostConstruct
    public void init() {
        log.warn("⚠️✅ TODO: ForI в методе getUsers должен заменить MapperClass", AccountService.class.getName() + ".java");
    }

    public List<UserDataBalanceDTO> getUserBalance(List<Integer> userIds) {
        return serviceUtil.getUserBalance(userIds);
    }

    public List<UserDataDTO> getUsers() {
        List<UserDataDTO> dtoList = new ArrayList<>();
        List<UsersDataTable> usersList = usersDataRepository.findAll();
        for (UsersDataTable user : usersList) {//todo
           UserDataDTO userDataDTO = userDataMapper.toUserDataDTO(user);
            dtoList.add(userDataDTO);
        }
        return dtoList;
    }



}
