package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.domain.UserAuthTokenTable;
import com.example.MaslyakBank_client.domain.UserBalanceTable;
import com.example.MaslyakBank_client.domain.UserDataTable;
import com.example.MaslyakBank_client.dto.endpointsDTOs.UserChangeStatusDTO;
import com.example.MaslyakBank_client.dto.tablesDTOs.UserBalanceDTO;
import com.example.MaslyakBank_client.dto.tablesDTOs.UserDataDTO;
import com.example.MaslyakBank_client.mappers.UserAuthTokenMapper;
import com.example.MaslyakBank_client.mappers.UserBalanceMapper;
import com.example.MaslyakBank_client.mappers.UserDataMapper;
import com.example.MaslyakBank_client.repository.UserAuthTokenRepository;
import com.example.MaslyakBank_client.repository.UserBalanceRepository;
import com.example.MaslyakBank_client.repository.UserDataRepository;
import com.example.MaslyakBank_client.util.ServiceUtil;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ManagementService {

    private final UserDataRepository userDataRepository;
    private final UserBalanceRepository userBalanceRepository;
    private final UserAuthTokenRepository userAuthTokenRepository;
    private final ServiceUtil serviceUtil;
    private final UserDataMapper userDataMapper;
    private final UserBalanceMapper userBalanceMapper;
    private final UserAuthTokenMapper userAuthTokenMapper;

    @Autowired
    public ManagementService(
            UserDataRepository userDataRepository,
            UserBalanceRepository userBalanceRepository,
            UserAuthTokenRepository userAuthTokenRepository,
            ServiceUtil serviceUtil,
            UserDataMapper userDataMapper,
            UserBalanceMapper userBalanceMapper,
            UserAuthTokenMapper userAuthTokenMapper
    ) {
        this.userDataRepository = userDataRepository;
        this.userBalanceRepository = userBalanceRepository;
        this.userAuthTokenRepository = userAuthTokenRepository;
        this.serviceUtil = serviceUtil;
        this.userDataMapper = userDataMapper;
        this.userBalanceMapper = userBalanceMapper;
        this.userAuthTokenMapper = userAuthTokenMapper;
    }

    @PostConstruct
    public void init() {
        log.warn("✅✅ TODO: В методе saveUser, saveUserBalance, saveUserAuthToken превращение в другой обьект - должен заменить MapperClass", ManagementService.class.getName() + ".java");
        log.warn("✅✅ TODO: Все if-else, которые связаны с валидацией должны быть переведены в отдельный класс Validator и оттудого вызываться методы про эту валидацию должны", ManagementService.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Все Успешные ответы сервера перевести в формат JSON при выводе правильно подставить что выводить", ManagementService.class.getName() + ".java");
    }

    public List<UserBalanceDTO> getUserBalance(List<Integer> userIds) {
        return serviceUtil.getUserBalance(userIds);
    }


    public String changeStatus(List<UserChangeStatusDTO> userData) {
        try {
            List<UserDataTable> usersToUpdate = userDataRepository.findAllById(
                    userData.stream().map(UserChangeStatusDTO::getUser_id).toList()
            );

            for (UserDataTable user : usersToUpdate) {
                UserChangeStatusDTO dto = userData.stream()
                        .filter(u -> u.getUser_id() == user.getId())
                        .findFirst()
                        .orElse(null);

                if (dto != null) {
                    user.setStatus(dto.isGetStatus());
                }
            }

            userDataRepository.saveAll(usersToUpdate);
            return "Status updated successfully";//todo
        } catch (Exception e) {
            return "Error updating status: " + e.getMessage();
        }
    }


    public String changeDataUser(int id, String newLogin) {
        try {
            //ушла проверка на существуещий айди и логин на уровень выше
            userDataRepository.changeLogin(id, newLogin);
            return "Login updated successfully";//todo
        } catch (Exception e) {
            return "Error updating login: " + e.getMessage();
        }
    }


    public String changeEmail(int id, String newEmail) {
        try {
            //ушла проверка на существуещий эмейл на уровень выше  todo
            UserDataTable user = userDataRepository.findById(id).get();
            user.setEmail(newEmail);
            userDataRepository.save(user);

            return "Email changed successfully";//todo

        } catch (Exception e) {
            return "Error changing email: " + e.getMessage();
        }
    }

    public String deleteUserById(List<Integer> userIds) {
        try {
            //ушла проверка на существуещий айди  на уровень выше  todo
            List<Integer> existingIds = userDataRepository.findAllById(userIds)
                    .stream()
                    .map(UserDataTable::getId)
                    .toList();
            userDataRepository.deleteAllById(existingIds);
            return "Users deleted successfully";//todo
        } catch (Exception e) {
            return "Error deleting users: " + e.getMessage();
        }
    }


    @Transactional
    public String createUser(UserDataDTO userDataDTO) {
        try {
            //ушла проверка на существуещий эмейл и логина на уровень выше  todo
            UserDataTable user = saveUser(userDataDTO);
            saveUserBalance(user);
            saveUserAuthToken(user);

            return "User created successfully";//todo
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    private UserDataTable saveUser(UserDataDTO userDataDTO) { //todo
        UserDataTable user = userDataMapper.toUsersDataTable(userDataDTO);
        return userDataRepository.save(user);
    }

    private void saveUserBalance(UserDataTable user) { //todo
        UserBalanceTable userBalance = userBalanceMapper.toUsersBalanceDataTable(user);
        userBalanceRepository.save(userBalance);
    }

    private void saveUserAuthToken(UserDataTable user) { //todo
        UserAuthTokenTable userAuthToken = userAuthTokenMapper.toUsersAuthTokenTable(user);
        userAuthTokenRepository.save(userAuthToken);
    }


}
