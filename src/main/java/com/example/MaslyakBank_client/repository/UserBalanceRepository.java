package com.example.MaslyakBank_client.repository;

import com.example.MaslyakBank_client.domain.UserBalanceTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalanceTable, Integer> {

    @Query("SELECT u.balance_usd FROM UserBalanceTable u WHERE u.user_id = :userId")
    Optional<String> findBalanceByUserId(@Param("userId") Integer userId);

}
