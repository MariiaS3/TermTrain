package com.termtrain.authutils.repository;

import com.termtrain.authutils.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AccountRepository  extends JpaRepository<Account, UUID>{
    Account findByUsername(String email);
}
