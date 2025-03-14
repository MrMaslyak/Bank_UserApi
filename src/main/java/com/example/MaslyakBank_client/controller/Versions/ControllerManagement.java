package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.dto.UserRequestDTO;
import com.example.MaslyakBank_client.service.ManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/v2")
@Validated
public class ControllerManagement {

    private final ManagementService accountManagement;

    @GetMapping("/user_balance")// /balance, если я ему не передаю отдельные айди, которые я хочу он будет выдавать весь список юзеров со веми балансами
    public List<UserDataBalanceDTO> getBalanceUser(@RequestHeader("user_id") List<Integer> userIds) {
        return accountManagement.getUserBalance(userIds);
    }

    @PostMapping("/update_status")//PUT /user/change/status
    public String updateUserStatus(@RequestBody  List<UserRequestDTO> userRequestDTOList) {//todo  дорозабраться с валидации лиса
        return accountManagement.updateUserStatus(userRequestDTOList);
    }

    @PutMapping("/change_login")// /user , меняю не сильно выжные данные передаю, те данные которые  хочу изменить - не секьюрные поля от того что не зависит бизнес логики
    public String changeLogin(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return accountManagement.changeLogin(userRequestDTO.getUser_id(), userRequestDTO.getLogin());
    }

    @PatchMapping("/change_email")// /user/change/email-pasword
    public String changeEmail(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return accountManagement.changeEmail(userRequestDTO.getUser_id(), userRequestDTO.getEmail());
    }

    @DeleteMapping("/delete_user") // /user
    public String deleteUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return accountManagement.deleteUserById(userRequestDTO.getUsers_id());
    }

    @PostMapping("/create_user")//  /user
    public String createUser(@RequestBody @Valid UserDataDTO userDataDTO) {
        return accountManagement.createUser(userDataDTO);
    }


}
