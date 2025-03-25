package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.tablesDTOs.UserBalanceDTO;
import com.example.MaslyakBank_client.dto.tablesDTOs.UserDataDTO;
import com.example.MaslyakBank_client.dto.endpointsDTOs.UserRequestDTO;
import com.example.MaslyakBank_client.service.ManagementService;
import com.example.MaslyakBank_client.validator.UserValidator;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/v2")
@Validated
@Slf4j
public class ManagementController {

    private final ManagementService accountManagement;
    private final UserValidator userValidator;

    @Autowired
    public ManagementController(UserValidator userValidator, ManagementService accountManagement) {
        this.userValidator = userValidator;
        this.accountManagement = accountManagement;
    }

    @PostConstruct
    public void init() {
        log.warn("⚠️⚠️ TODO: Дорозабраться с валидации листа DTO", ManagementController.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Поменять @PostMapping в /update_status на @PutMapping({}:{})", ManagementController.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Поменять @PutMapping в /change_login на @PatchMapping", ManagementController.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Сделать новый нейминг во все CRUD endpoints с user -> /user ", ManagementController.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Сделать новый нейминг во все change секьюрные данные -> /user/change/email и status и password ", ManagementController.class.getName() + ".java");
        log.warn("⚠️⚠️ TODO: Создать под каждый endpoint свой DTO, который будет валидировать", ManagementController.class.getName() + ".java");
    }


    @GetMapping("/user_balance")
    public List<UserBalanceDTO> getBalanceUser(@RequestHeader("user_id") List<Integer> userIds) {
        return accountManagement.getUserBalance(userIds);
    }

    @PostMapping("/update_status")
    public String updateUserStatus(@RequestBody List<UserRequestDTO> userRequestDTOList) {//todo
        return accountManagement.updateUserStatus(userRequestDTOList);
    }
    
    @PutMapping("/change_login")
    public String changeLogin(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        userValidator.validateLogin(userRequestDTO.getLogin());
        userValidator.validateUserId(userRequestDTO.getUser_id());
        return accountManagement.changeLogin(userRequestDTO.getUser_id(), userRequestDTO.getLogin());
    }

    @PatchMapping("/change_email")
    public String changeEmail(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        userValidator.validateEmail(userRequestDTO.getEmail());
        return accountManagement.changeEmail(userRequestDTO.getUser_id(), userRequestDTO.getEmail());
    }

    @DeleteMapping("/delete_user")
    public String deleteUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        userValidator.validateUserIds(userRequestDTO.getUsers_id());
        return accountManagement.deleteUserById(userRequestDTO.getUsers_id());
    }

    @PostMapping("/create_user")
    public String createUser(@RequestBody @Valid UserDataDTO userDataDTO) {
        userValidator.validateEmail(userDataDTO.getEmail());
        userValidator.validateLogin(userDataDTO.getLogin());
        return accountManagement.createUser(userDataDTO);
    }



}
