package com.example.MaslyakBank_client.mappers;


import com.example.MaslyakBank_client.domain.UserAuthTokenTable;
import com.example.MaslyakBank_client.domain.UserDataTable;
import com.example.MaslyakBank_client.dto.tableDTOs.UserAuthTokenDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAuthTokenMapper {

    UserAuthTokenDTO  toUserAuthTokenDTO(UserAuthTokenTable userAuthTokenDataTable);


    default UserAuthTokenTable toUsersAuthTokenTable(UserDataTable user) {
        if (user == null) {
            return null;
        }
        UserAuthTokenTable userAuthToken = new UserAuthTokenTable();
        userAuthToken.setUser_id(user);
        userAuthToken.setCreated_at(java.time.LocalDateTime.now());
        return userAuthToken;
    }

}
