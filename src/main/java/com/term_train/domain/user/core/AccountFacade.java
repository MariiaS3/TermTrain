package com.term_train.domain.user.core;

import com.term_train.domain.user.core.dto.AccountDto;
import com.term_train.domain.user.core.model.Account;
import com.term_train.domain.user.infrastructure.AccountDatabase;
import com.term_train.domain.user.infrastructure.AccountDatabaseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountFacade implements AccountAction {

    private final AccountDatabaseAdapter userDatabase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createUser(AccountDto userDto) {
        Account user = userDatabase.findByUsername(userDto.getUsername());
        if (user != null) {
            throw new RuntimeException("This username already exist");
        }
        Account account = userDto.toUser();
        account.changePassword(passwordEncoder.encode(userDto.getPassword()));
        return userDatabase.saveAndFlush(account).getId().toString();
    }

    @Override
    public AccountDto verifyUserByUsername(String username) {
        Account account = userDatabase.findByUsername(username);
        if (Objects.isNull(account)) {
            throw new RuntimeException("user not exist with this email: " + username);
        }
        return account.toUserDTO();
    }
}
