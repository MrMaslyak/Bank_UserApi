package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.domain.UsersAuthTokenTable;
import com.example.MaslyakBank_client.domain.UsersBalanceDataTable;
import com.example.MaslyakBank_client.domain.UsersDataTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserRequestDTO;
import com.example.MaslyakBank_client.repository.UserAuthTokenRepository;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import com.example.MaslyakBank_client.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ManagementService {

    private static final String EMAIL_PATTERN =
            "^(?![!#$%&'*+/=?^_`{|}~])[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    "(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                    "@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+" +
                    "[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|" +
                    "[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|" +
                    "[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|" +
                    "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";


    private final UsersDataRepository usersDataRepository;
    private final UserBalanceTableRepository userBalanceTableRepository;
    private final UserAuthTokenRepository userAuthTokenRepository;
    private final ServiceUtil  serviceUtil;

    @Autowired
    public ManagementService(
            UsersDataRepository usersDataRepository,
            UserBalanceTableRepository userBalanceTableRepository,
            UserAuthTokenRepository userAuthTokenRepository,
            ServiceUtil serviceUtil
    ) {
        this.usersDataRepository = usersDataRepository;
        this.userBalanceTableRepository = userBalanceTableRepository;
        this.userAuthTokenRepository = userAuthTokenRepository;
        this.serviceUtil = serviceUtil;
    }

    public List<UserDataBalanceDTO> getUserBalance(List<Integer> userIds) {
        return serviceUtil.getUserBalance(userIds);
    }


    public String updateUserStatus(List<UserRequestDTO> userData) {
        try {
            for (UserRequestDTO user : userData) {
                usersDataRepository.setStatus(user.getUser_id(), user.isGetStatus());
            }
            return "Status updated successfully";
        } catch (Exception e) {
            return "Error updating status: " + e.getMessage();
        }
    }


    public String changeLogin(int id, String newLogin) {
        try {
            UsersDataTable user = usersDataRepository.findById(id)
                    .orElseThrow(() -> new Exception("User not found"));

            user.setLogin(newLogin);
            usersDataRepository.save(user);

            return "Login updated successfully";
        } catch (Exception e) {
            return "Error updating login: " + e.getMessage();
        }
    }

    public String changeEmail(int id, String newEmail) {
        try {
            if (usersDataRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(newEmail))) {
                return "Email already exists";
            }
            if (!newEmail.matches(EMAIL_PATTERN)) {
                return "Invalid email format";
            } else {
                usersDataRepository.changeEmail(id, newEmail);
                return "Email changed successfully";
            }
        } catch (Exception e) {
            return "Error changing email: " + e.getMessage();
        }
    }

    public String deleteUserById(List<Integer> userIds) {
        try {
            List<Integer> existingIds = usersDataRepository.findAllById(userIds).stream()
                    .map(UsersDataTable::getId)
                    .toList();
            if (existingIds.isEmpty()) {
                return "Users not found";
            }
            usersDataRepository.deleteAllById(existingIds);
            return "Users deleted successfully";
        } catch (Exception e) {
            return "Error deleting users: " + e.getMessage();
        }
    }

    public String createUser(UserRequestDTO userRequestDTO) {
        try {
            if (usersDataRepository.findAll().stream().anyMatch(user -> user.getLogin().equals(userRequestDTO.getLogin()))) {
                return "Login already exists";
            }
            if (!userRequestDTO.getEmail().matches(EMAIL_PATTERN)) {
                return "Invalid email format";
            }

            UsersDataTable user = new UsersDataTable();
            user.setLogin(userRequestDTO.getLogin());
            user.setEmail(userRequestDTO.getEmail());
            user.setPassword(userRequestDTO.getPassword());
            user.setCreated_at(Date.from(java.time.Instant.now()));
            user.setStatus(false);

            user = usersDataRepository.save(user);

            UsersBalanceDataTable userBalance = new UsersBalanceDataTable();
            userBalance.setUser_id(user);
            userBalance.setBalance_usd("0.00");
            userBalanceTableRepository.save(userBalance);

            UsersAuthTokenTable userAuthToken = new UsersAuthTokenTable();
            userAuthToken.setUser_id(user);
            userAuthToken.setToken("null");
            userAuthToken.setCreated_at(Date.from(java.time.Instant.now()));
            userAuthTokenRepository.save(userAuthToken);

            return "User created successfully";
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }


}
