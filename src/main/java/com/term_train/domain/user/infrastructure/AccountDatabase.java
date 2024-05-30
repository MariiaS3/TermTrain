package com.term_train.domain.user.infrastructure;

import com.term_train.domain.user.core.model.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDatabase {
    @NotNull
    Account saveAndFlush(Account user);

    Account findByUsername(String username);
}
