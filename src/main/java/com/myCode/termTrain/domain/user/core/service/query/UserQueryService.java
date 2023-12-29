package com.myCode.termTrain.domain.user.core.service.query;

import com.myCode.termTrain.domain.user.core.UserAction;
import com.myCode.termTrain.domain.user.core.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserAction userAction;

    public UserDto verifyUserByUsername(String username) {
        return userAction.verifyUserByUsername(username);
    }
}
