package com.example.MaslyakBank_client.repository;

import com.example.MaslyakBank_client.domain.UsersTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
 public  interface UsersRepository extends JpaRepository<UsersTable, Integer> {


    @Query("SELECT u.login FROM UsersTable u WHERE u.user_id = :userId")
    Optional<String> findLoginByUserId(@Param("userId") Integer userId);

    @Override
    List<UsersTable> findAll();

}
