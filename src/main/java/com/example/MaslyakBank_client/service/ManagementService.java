package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.domain.UsersAuthTokenTable;
import com.example.MaslyakBank_client.domain.UsersBalanceDataTable;
import com.example.MaslyakBank_client.domain.UsersDataTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.dto.UserRequestDTO;
import com.example.MaslyakBank_client.mappers.UserAuthTokenMapper;
import com.example.MaslyakBank_client.mappers.UserBalanceMapper;
import com.example.MaslyakBank_client.mappers.UserDataMapper;
import com.example.MaslyakBank_client.repository.UserAuthTokenRepository;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import com.example.MaslyakBank_client.util.ServiceUtil;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class ManagementService {

    private final UsersDataRepository usersDataRepository;
    private final UserBalanceTableRepository userBalanceTableRepository;
    private final UserAuthTokenRepository userAuthTokenRepository;
    private final ServiceUtil  serviceUtil;
    private final UserDataMapper  userDataMapper;
    private final UserBalanceMapper  userBalanceMapper;
    private final UserAuthTokenMapper  userAuthTokenMapper;

    @Autowired
    public ManagementService(
            UsersDataRepository usersDataRepository,
            UserBalanceTableRepository userBalanceTableRepository,
            UserAuthTokenRepository userAuthTokenRepository,
            ServiceUtil serviceUtil,
            UserDataMapper  userDataMapper,
            UserBalanceMapper  userBalanceMapper,
            UserAuthTokenMapper  userAuthTokenMapper
    ) {
        this.usersDataRepository = usersDataRepository;
        this.userBalanceTableRepository = userBalanceTableRepository;
        this.userAuthTokenRepository = userAuthTokenRepository;
        this.serviceUtil = serviceUtil;
        this.userDataMapper = userDataMapper;
        this.userBalanceMapper = userBalanceMapper;
        this.userAuthTokenMapper = userAuthTokenMapper;
    }

    @PostConstruct
    public void init() {
        log.warn("⚠️✅ TODO: В методе saveUser, saveUserBalance, saveUserAuthToken превращение в другой обьект - должен заменить MapperClass", ManagementService.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Все if-else, которые связаны с валидацией должны быть переведены в отдельный класс Validator и оттудого вызываться методы про эту валидацию должны", ManagementService.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Все Успешные ответы сервера перевести в формат JSON при выводе правильно подставить что выводить", ManagementService.class.getName() + ".java");
    }

    public List<UserDataBalanceDTO> getUserBalance(List<Integer> userIds) {
        return serviceUtil.getUserBalance(userIds);
    }


    public String updateUserStatus(List<UserRequestDTO> userData) {
        try {
            for (UserRequestDTO user : userData) {
                usersDataRepository.setStatus(user.getUser_id(), user.isGetStatus());
            }
            return "Status updated successfully";//todo
        } catch (Exception e) {
            return "Error updating status: " + e.getMessage();
        }
    }


    public String changeLogin(int id, String newLogin) {
        try {
            UsersDataTable user = usersDataRepository.findById(id)
                    .orElseThrow(() -> new Exception("User not found"));//todo

            user.setLogin(newLogin);
            usersDataRepository.save(user);

            return "Login updated successfully";//todo
        } catch (Exception e) {
            return "Error updating login: " + e.getMessage();
        }
    }


    public String changeEmail(int id, String newEmail) {
        try {
            if (usersDataRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(newEmail))) {
                return "Email already exists";
            } // todo
            else {
                usersDataRepository.changeEmail(id, newEmail);
                return "Email changed successfully";//todo
            }
        } catch (Exception e) {
            return "Error changing email: " + e.getMessage();
        }
    }

    public String deleteUserById(List<Integer> userIds) {
        try {
            List<Integer> existingIds = usersDataRepository.findAllById(userIds).stream().map(UsersDataTable::getId).toList();
            if (existingIds.isEmpty()) {
                return "Users not found";
            }// todo
            usersDataRepository.deleteAllById(existingIds);
            return "Users deleted successfully";//todo
        } catch (Exception e) {
            return "Error deleting users: " + e.getMessage();
        }
    }


   @Transactional
    public String createUser(UserDataDTO userDataDTO) {
        try {
            if (usersDataRepository.findAll().stream().anyMatch(user -> user.getLogin().equals(userDataDTO.getLogin()))) {
                return "Login already exists";
            } // todo

            UsersDataTable user = saveUser(userDataDTO);
            saveUserBalance(user);
            saveUserAuthToken(user);

            return "User created successfully";//todo
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    private UsersDataTable saveUser(UserDataDTO userDataDTO) { //todo
        UsersDataTable user = userDataMapper.toUsersDataTable(userDataDTO);
        return usersDataRepository.save(user);
    }

    private void saveUserBalance(UsersDataTable user) { //todo
        UsersBalanceDataTable userBalance = userBalanceMapper.toUsersBalanceDataTable(user);
        userBalanceTableRepository.save(userBalance);
    }

    private void saveUserAuthToken(UsersDataTable user) { //todo
        UsersAuthTokenTable  userAuthToken = userAuthTokenMapper.toUsersAuthTokenTable(user);
        userAuthTokenRepository.save(userAuthToken);
    }





}
