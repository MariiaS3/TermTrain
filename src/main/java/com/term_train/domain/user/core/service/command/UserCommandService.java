package com.term_train.domain.user.core.service.command;

import com.term_train.domain.user.core.UserAction;
import com.term_train.domain.user.core.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserAction userAction;

    public String createUser(UserDto userDto) {
        return userAction.createUser(userDto);
    }

}
