package com.myCode.termTrain.domain.user.core;

import com.myCode.termTrain.domain.user.core.dto.UserDto;

public interface UserAction {
    String createUser(UserDto userDto);

    UserDto verifyUserByUsername(String username);
}
