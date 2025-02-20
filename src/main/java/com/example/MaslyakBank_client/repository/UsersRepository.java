package com.example.MaslyakBank_client.repository;

import com.example.MaslyakBank_client.domain.UsersTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersTable, Integer> {


    @Query("SELECT u.login FROM UsersTable u WHERE u.user_id = :userId")
    Optional<String> findLoginByUserId(@Param("userId") Integer userId);

    @Query("SELECT u.user_id FROM UsersTable u WHERE u.login IN :logins")
    List<String> findUserIdByLogin(@Param("logins") List<String> logins);

    Optional<UsersTable> findLoginByUserId(String login);


    @Transactional
    @Modifying
    @Query("UPDATE UsersTable u SET u.disabled = :disabled WHERE u.user_id = :userId")
    void setDisabled(@Param("userId") Integer userId, @Param("disabled") boolean disabled);


    @Override
    List<UsersTable> findAll();

    @Override
    List<UsersTable> findAllById(Iterable<Integer> integers);


}
