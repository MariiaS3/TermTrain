package com.term_train.domain.user.core;

import com.term_train.domain.user.core.dto.UserDto;

public interface UserAction {
    String createUser(UserDto userDto);

    UserDto verifyUserByUsername(String username);
}
