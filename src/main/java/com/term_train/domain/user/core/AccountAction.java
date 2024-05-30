package com.term_train.domain.user.core;

import com.term_train.domain.user.core.dto.AccountDto;
import com.term_train.domain.user.core.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAction {
    String createUser(AccountDto userDto);

    AccountDto verifyUserByUsername(String username);
}
