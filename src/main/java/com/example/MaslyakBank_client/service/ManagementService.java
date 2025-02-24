package com.example.MaslyakBank_client.service;

import com.example.MaslyakBank_client.dto.UserRequestDTO;
import com.example.MaslyakBank_client.repository.UserBalanceTableRepository;
import com.example.MaslyakBank_client.repository.UsersDataRepository;
import org.springframework.stereotype.Service;
import com.example.MaslyakBank_client.domain.UsersDataTable;

import java.util.List;


@Service
public class ManagementService extends ServiceCore {

    private static final String EMAIL_PATTERN =
            "^(?![!#$%&'*+/=?^_`{|}~])[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    "(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                    "@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+" +
                    "[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|" +
                    "[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|" +
                    "[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|" +
                    "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";

    public ManagementService(UserBalanceTableRepository userBalanceTableRepository, UsersDataRepository usersDataRepository) {
        super(userBalanceTableRepository, usersDataRepository);
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
            usersDataRepository.changeLogin(id, newLogin);
            return "Login changed successfully";
        } catch (Exception e) {
            return "Error changing login: " + e.getMessage();
        }
    }

    public String changeEmail(int id, String newEmail) {
        try {
            if (usersDataRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(newEmail))) {
                return "Email already exists";
            }
            if (!newEmail.matches(EMAIL_PATTERN)) {
                return "Invalid email format";
            }else {
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




}
