package com.example.MaslyakBank_client.repository;

import com.example.MaslyakBank_client.domain.UserDataTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataTable, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE UserDataTable u SET u.status = :status WHERE u.id = :userId")
    void setStatus(@Param("userId") Integer userId, @Param("status") boolean status);


    @Transactional
    @Modifying
    @Query("UPDATE UserDataTable u SET u.email = :newEmail WHERE u.id = :userId")
    void changeEmail(@Param("userId") int userId, @Param("newEmail") String newEmail);


    @Query("SELECT u.login FROM UserDataTable u WHERE u.id = :userId")
    Optional<String> findLoginByUserId(@Param("userId") Integer userId);

    @Override
    List<UserDataTable> findAll();

    @Override
    List<UserDataTable> findAllById(Iterable<Integer> integers);

    Optional<UserDataTable> findByEmail(String email);
    Optional<UserDataTable> findByLogin(String login);
    Optional<UserDataTable> findById(int id);




}
