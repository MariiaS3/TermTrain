package com.myCode.termTrain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myCode.termTrain.model.Account;

public interface AccountRepository  extends JpaRepository<Account, UUID>{
    Account findByUsername(String email);
}
