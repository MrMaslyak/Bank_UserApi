package com.example.MaslyakBank_client.mappers;


import com.example.MaslyakBank_client.domain.UsersAuthTokenTable;
import com.example.MaslyakBank_client.domain.UsersDataTable;
import com.example.MaslyakBank_client.dto.UserAuthTokenDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAuthTokenMapper {

    UserAuthTokenDTO  toUserAuthTokenDTO(UsersAuthTokenTable userAuthTokenDataTable);


    default UsersAuthTokenTable toUsersAuthTokenTable(UsersDataTable user) {
        if (user == null) {
            return null;
        }
        UsersAuthTokenTable userAuthToken = new UsersAuthTokenTable();
        userAuthToken.setUser_id(user);
        userAuthToken.setCreated_at(java.time.LocalDateTime.now());
        return userAuthToken;
    }

}
