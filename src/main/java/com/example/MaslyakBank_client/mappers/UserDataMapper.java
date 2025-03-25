package com.example.MaslyakBank_client.mappers;


import com.example.MaslyakBank_client.domain.UserDataTable;
import com.example.MaslyakBank_client.dto.tablesDTOs.UserDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDataMapper {



    @Mapping(target = "id", source = "user_id")
    @Mapping(target = "created_at", expression = "java(java.time.LocalDateTime.now())")
    UserDataTable toUsersDataTable(UserDataDTO userDataDTO);

    @Mapping(target = "user_id", source = "id")
    UserDataDTO toUserDataDTO(UserDataTable user);

}
