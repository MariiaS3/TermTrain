package com.term_train.ddd.user.domain.service;

import com.term_train.ddd.user.domain.AccountFacade;
import com.term_train.ddd.user.domain.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountFacade userAction;

    public String createUser(AccountDto userDto) {
        return userAction.createUser(userDto);
    }

    public AccountDto verifyUserByUsername(String username) {
        return userAction.verifyUserByUsername(username);
    }

}
