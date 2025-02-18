package com.example.MaslyakBank_client.controller.Versions;


import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import com.example.MaslyakBank_client.dto.UserIdRequestDTO;
import com.example.MaslyakBank_client.service.ServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class BankControllerV2 {


    private final ServiceV2 serviceV2;

    @GetMapping("/data_head")
    public List<UserDataBalanceDTO> getDataBalanceUsersHead(@RequestHeader("users_id") List<Integer> userIds) {
        return serviceV2.getUsersBalanceData(userIds);
    }

    @GetMapping("/setDisabled")
    public String updateUserDisabled(@RequestParam("user_id") int userId, @RequestParam("disabled") boolean  disabled ) {
        return serviceV2.updateUserDisabled(userId, disabled);
    }

    @PostMapping("/data_body")
    public List<UserDataBalanceDTO> getDataBalanceUsersBody(@RequestBody UserIdRequestDTO userId) {
        return serviceV2.getUsersByBody(userId.getUserId());
    }





}
