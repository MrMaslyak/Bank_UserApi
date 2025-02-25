package com.example.MaslyakBank_client.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthTokenRepository  extends JpaRepository<UserAuthTokenRepository, Integer> {

    UserAuthTokenRepository findByToken(String token);

}
