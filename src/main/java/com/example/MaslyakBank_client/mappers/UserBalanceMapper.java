package com.example.MaslyakBank_client.mappers;


import com.example.MaslyakBank_client.domain.UserBalanceTable;
import com.example.MaslyakBank_client.domain.UserDataTable;
import com.example.MaslyakBank_client.dto.tablesDTOs.UserBalanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserBalanceMapper {

    @Mapping(target = "userId", source = "user_id.id")
    @Mapping(target = "balance", source = "balance_usd")
    @Mapping(target = "login", source = "user_id.login")
    UserBalanceDTO toUserDataBalanceDTO(UserBalanceTable userBalanceDataTable);


    default UserBalanceTable toUsersBalanceDataTable(UserDataTable user) {
        if (user == null) {
            return null;
        }
        UserBalanceTable userBalance = new UserBalanceTable();
        userBalance.setUser_id(user);
        userBalance.setBalance_usd("0");
        return userBalance;
    }




}

