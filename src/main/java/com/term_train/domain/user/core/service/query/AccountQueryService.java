package com.term_train.domain.user.core.service.query;

import com.term_train.domain.user.core.AccountFacade;
import com.term_train.domain.user.core.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final AccountFacade accountFacade;

    public AccountDto verifyUserByUsername(String username) {
        return accountFacade.verifyUserByUsername(username);
    }
}
