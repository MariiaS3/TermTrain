package com.myCode.termTrain.domain.user.core.service.command;

import com.myCode.termTrain.domain.user.core.UserAction;
import com.myCode.termTrain.domain.user.core.dto.UserDto;
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
