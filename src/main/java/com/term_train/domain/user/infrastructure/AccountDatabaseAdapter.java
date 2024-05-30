package com.term_train.domain.user.infrastructure;

import com.term_train.domain.user.core.model.Account;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDatabaseAdapter implements AccountDatabase {

    private final AccountRepository userRepository;

    @NotNull
    @Override
    public Account saveAndFlush(Account user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Account findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
