package com.example.MaslyakBank_client.validator;

import com.example.MaslyakBank_client.domain.UserDataTable;
import com.example.MaslyakBank_client.dto.endpointsDTOs.UserChangeStatusDTO;
import com.example.MaslyakBank_client.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class UserValidator implements Validator {

    private final UserDataRepository userRepository;

    @Autowired
    public UserValidator(UserDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDataTable.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exist");
        }
    }

    public void validateLogin(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            throw new IllegalArgumentException("Login already exist");
        }
    }

    public void validateUserId(Integer userId) {
        if (userId == 0 || userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User with this id not exist");
        }
    }

    public void validateUserIds(List<Integer> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            throw new IllegalArgumentException("User IDs cannot be null or empty");
        }

        List<Integer> existingIds = userRepository.findAllById(userIds)
                .stream()
                .map(UserDataTable::getId)
                .toList();

        if (existingIds.isEmpty()) {
            throw new IllegalArgumentException("Users not found");
        }

        if (existingIds.size() != userIds.size()) {
            throw new IllegalArgumentException("Some users were not found");
        }
    }

    public void validateUserStatusList(List<UserChangeStatusDTO> userChangeStatusDTOList) {
        if (userChangeStatusDTOList == null || userChangeStatusDTOList.isEmpty()) {
            throw new IllegalArgumentException("User list cannot be null or empty");
        }

        List<Integer> userIds = userChangeStatusDTOList.stream()
                .map(UserChangeStatusDTO::getUser_id)
                .toList();

        List<Integer> existingIds = userRepository.findAllById(userIds)
                .stream()
                .map(UserDataTable::getId)
                .toList();

        if (existingIds.size() != userIds.size()) {
            throw new IllegalArgumentException("Some users were not found");
        }
    }




}
