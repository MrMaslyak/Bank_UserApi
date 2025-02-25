package com.example.MaslyakBank_client.repository;


import com.example.MaslyakBank_client.domain.UsersAuthTokenTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthTokenRepository  extends JpaRepository<UsersAuthTokenTable, Integer> {



}
