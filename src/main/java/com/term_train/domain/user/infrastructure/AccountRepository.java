package com.term_train.domain.user.infrastructure;

import com.term_train.domain.user.core.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByUsername(String username);
}
