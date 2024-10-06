package com.term_train.ddd.user.infrastructure.adapter;

import com.term_train.ddd.user.domain.model.Account;
import com.term_train.ddd.user.infrastructure.port.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDatabaseAdapter {

    private final AccountRepository userRepository;

    @NotNull
    public Account saveAndFlush(Account user) {
        return userRepository.saveAndFlush(user);
    }

    public Account findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
