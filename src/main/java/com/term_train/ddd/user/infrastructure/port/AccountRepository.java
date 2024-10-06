package com.term_train.ddd.user.infrastructure.port;

import com.term_train.ddd.user.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByUsername(String username);
}
