package com.term_train.ddd.user.domain;

import com.term_train.ddd.user.domain.dto.AccountDto;
import com.term_train.ddd.user.domain.model.Account;
import com.term_train.ddd.user.infrastructure.adapter.AccountDatabaseAdapter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountFacade {
    private final Logger LOG = LoggerFactory.getLogger(AccountFacade.class);
    private final AccountDatabaseAdapter userDatabase;
    private final PasswordEncoder passwordEncoder;


    public String createUser(AccountDto userDto) {
        Account user = userDatabase.findByUsername(userDto.getUsername());

        if (user != null) {
            throw new RuntimeException("This username already exist");
        }
        Account account = userDto.toUser();
        account.changePassword(passwordEncoder.encode(userDto.getPassword()));
        return userDatabase.saveAndFlush(account).getId().toString();
    }

    public AccountDto verifyUserByUsername(String email) {
        Account account = userDatabase.findByUsername(email);
        if (Objects.isNull(account)) {
            throw new RuntimeException("user not exist with this email: " + email);
        }
        return account.toUserDTO();
    }
}
