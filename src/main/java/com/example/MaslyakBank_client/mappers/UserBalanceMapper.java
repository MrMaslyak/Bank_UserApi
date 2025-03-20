package com.example.MaslyakBank_client.mappers;


import com.example.MaslyakBank_client.domain.UsersBalanceDataTable;
import com.example.MaslyakBank_client.dto.UserDataBalanceDTO;
import com.example.MaslyakBank_client.dto.UserDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserBalanceMapper {

    UsersBalanceDataTable toUsersBalanceDataTable(UserDataDTO userDataDTO);


    @Mapping(target = "userId", source = "user_id.id") // Маппинг userId из UsersBalanceDataTable
    @Mapping(target = "balance", source = "balance_usd") // Маппинг balance_usd в balance
    @Mapping(target = "login", source = "user_id.login") // Маппинг login из UsersDataTable
    UserDataBalanceDTO toUserDataBalanceDTO(UsersBalanceDataTable userBalanceDataTable);
}
