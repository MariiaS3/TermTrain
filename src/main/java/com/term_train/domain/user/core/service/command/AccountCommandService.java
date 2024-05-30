package com.term_train.domain.user.core.service.command;

import com.term_train.domain.user.core.AccountAction;
import com.term_train.domain.user.core.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    private final AccountAction userAction;

    public String createUser(AccountDto userDto) {
        return userAction.createUser(userDto);
    }

}
