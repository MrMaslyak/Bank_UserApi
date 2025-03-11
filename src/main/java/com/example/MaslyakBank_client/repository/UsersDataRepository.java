package com.example.MaslyakBank_client.repository;

import com.example.MaslyakBank_client.domain.UsersDataTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersDataRepository extends JpaRepository<UsersDataTable, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE UsersDataTable u SET u.status = :status WHERE u.id = :userId")
    void setStatus(@Param("userId") Integer userId, @Param("status") boolean status);


    @Transactional
    @Modifying
    @Query("UPDATE UsersDataTable u SET u.email = :newEmail WHERE u.id = :userId")
    void changeEmail(@Param("userId") int userId, @Param("newEmail") String newEmail);


    @Query("SELECT u.login FROM UsersDataTable u WHERE u.id = :userId")
    Optional<String> findLoginByUserId(@Param("userId") Integer userId);

    @Override
    List<UsersDataTable> findAll();

    @Override
    List<UsersDataTable> findAllById(Iterable<Integer> integers);



}
