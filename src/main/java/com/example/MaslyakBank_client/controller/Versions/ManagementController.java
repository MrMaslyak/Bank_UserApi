package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.endpointsDTOs.*;
import com.example.MaslyakBank_client.dto.tablesDTOs.UserBalanceDTO;
import com.example.MaslyakBank_client.dto.tablesDTOs.UserDataDTO;
import com.example.MaslyakBank_client.service.ManagementService;
import com.example.MaslyakBank_client.validator.process.UserValidator;
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
        log.info("✅✅ TODO: Поменять @PostMapping в /update_status на @PutMapping({}:{})", ManagementController.class.getName() + ".java");
        log.info("✅✅ TODO: Поменять @PutMapping в /change_login на @PatchMapping", ManagementController.class.getName() + ".java");
        log.info("✅✅ TODO: Сделать новый нейминг во все CRUD endpoints с user -> /user ", ManagementController.class.getName() + ".java");
        log.info("✅✅ TODO: Сделать новый нейминг во все change секьюрные данные -> /user/change/email и status и password ", ManagementController.class.getName() + ".java");
        log.info("✅✅ TODO: Создать под каждый endpoint свой DTO, который будет валидировать", ManagementController.class.getName() + ".java");
        log.warn("⚠️⚠️️⚠️️ TODO: Дорозабраться с валидации листа  List<UserChangeStatusDTO>", ManagementController.class.getName() + ".java");
        log.warn("⚠️️⚠️⚠️ TODO: Пофиксить @PutMapping(\"/change/status\") чтобы он возвращал список айди которые не удалось исполнять а остальные должны высполняться и все это через PUT", ManagementController.class.getName() + ".java");
        log.warn("⚠️️⚠️⚠️ TODO: Создать отдельный ендпоинт @PostMapping для (\"/change/status\")", ManagementController.class.getName() + ".java");
    }


    @GetMapping("/user_balance")// примерочный endpoint
    public List<UserBalanceDTO> getBalanceUser(@RequestHeader("user_id") List<Integer> userIds) {
        return accountManagement.getUserBalance(userIds);
    }

    @PutMapping("user/change/status")
    public  List<UserChangeStatusDTO> changeStatus(@RequestBody  List<UserChangeStatusDTO> userChangeStatusDTOList) {//todo
        userValidator.validateUserStatusList(userChangeStatusDTOList);
        return accountManagement.changeStatus(userChangeStatusDTOList);
    }
    
    @PatchMapping("/user")
    public UserChangeDTO changeDataUser(@RequestBody @Valid UserChangeDTO userChangeDTO) {
        userValidator.validateLogin(userChangeDTO.getLogin());
        userValidator.validateUserId(userChangeDTO.getUser_id());
        return accountManagement.changeDataUser(userChangeDTO.getUser_id(), userChangeDTO.getLogin(), userChangeDTO);
    }

    @PutMapping("user/change/email")
    public UserChangeEmailDTO changeEmail(@RequestBody @Valid UserChangeEmailDTO userChangeEmailDTO) {
        userValidator.validateEmail(userChangeEmailDTO.getEmail());
        userValidator.validateUserId(userChangeEmailDTO.getUser_id());
        return accountManagement.changeEmail(userChangeEmailDTO.getUser_id(), userChangeEmailDTO.getEmail(), userChangeEmailDTO);
    }

    @DeleteMapping("/user")
    public UserDeleteDTO deleteUser(@RequestBody @Valid UserDeleteDTO userDeleteDTO) {
        userValidator.validateUserIds(userDeleteDTO.getUsers_id());
        return accountManagement.deleteUserById(userDeleteDTO.getUsers_id());
    }

    @PostMapping("/user")
    public UserDataDTO createUser(@RequestBody @Valid UserDataDTO userDataDTO) {
        userValidator.validateEmail(userDataDTO.getEmail());
        userValidator.validateLogin(userDataDTO.getLogin());
        return accountManagement.createUser(userDataDTO);
    }



}
