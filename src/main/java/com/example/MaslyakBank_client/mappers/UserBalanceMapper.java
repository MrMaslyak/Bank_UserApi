package com.example.MaslyakBank_client.mappers;


import com.example.MaslyakBank_client.domain.UsersBalanceDataTable;
import com.example.MaslyakBank_client.domain.UsersDataTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserBalanceMapper {

    @Mapping(target = "userId", source = "user_id.id")
    @Mapping(target = "balance", source = "balance_usd")
    @Mapping(target = "login", source = "user_id.login")
    UserDataBalanceDTO toUserDataBalanceDTO(UsersBalanceDataTable userBalanceDataTable);


    default UsersBalanceDataTable toUsersBalanceDataTable(UsersDataTable user) {
        if (user == null) {
            return null;
        }
        UsersBalanceDataTable userBalance = new UsersBalanceDataTable();
        userBalance.setUser_id(user);
        userBalance.setBalance_usd("0");
        return userBalance;
    }



}

