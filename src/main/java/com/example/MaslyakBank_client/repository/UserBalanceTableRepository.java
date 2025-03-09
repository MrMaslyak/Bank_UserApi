package com.example.MaslyakBank_client.repository;

import com.example.MaslyakBank_client.domain.UsersBalanceDataTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBalanceTableRepository extends JpaRepository<UsersBalanceDataTable, Integer> {

    @Query("SELECT u.balance_usd FROM UsersBalanceDataTable u WHERE u.id = :userId")
    Optional<String> findBalanceByUserId(@Param("userId") Integer userId);




}
