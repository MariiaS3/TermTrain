package com.term_train.domain.user.core.service.query;

import com.term_train.domain.user.core.AccountAction;
import com.term_train.domain.user.core.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountQueryService {

    private final AccountAction userAction;

    public AccountDto verifyUserByUsername(String username) {
        return userAction.verifyUserByUsername(username);
    }
}
