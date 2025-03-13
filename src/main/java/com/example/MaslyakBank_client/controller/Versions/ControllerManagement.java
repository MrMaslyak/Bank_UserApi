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

    @GetMapping("/user_balance")
    public List<UserDataBalanceDTO> getBalanceUser(@RequestHeader("user_id") List<Integer> userIds) {
        return accountManagement.getUserBalance(userIds);
    }

    @PostMapping("/update_status")
    public String updateUserStatus(@RequestBody  List<UserRequestDTO> userRequestDTOList) {
        return accountManagement.updateUserStatus(userRequestDTOList);
    }

    @PutMapping("/change_login")
    public String changeLogin(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return accountManagement.changeLogin(userRequestDTO.getUser_id(), userRequestDTO.getLogin());
    }

    @PatchMapping("/change_email")
    public String changeEmail(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return accountManagement.changeEmail(userRequestDTO.getUser_id(), userRequestDTO.getEmail());
    }

    @DeleteMapping("/delete_user")
    public String deleteUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return accountManagement.deleteUserById(userRequestDTO.getUsers_id());
    }

    @PostMapping("/create_user")
    public String createUser(@RequestBody @Valid UserDataDTO userDataDTO) {
        return accountManagement.createUser(userDataDTO);
    }


}
