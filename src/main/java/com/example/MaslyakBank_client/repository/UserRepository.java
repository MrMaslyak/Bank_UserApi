package com.example.MaslyakBank_client.repository;

import com.example.MaslyakBank_client.domain.UserBalanceTable;
import com.example.MaslyakBank_client.domain.UserBalanceTable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<UserBalanceTable, Long> {
}
