package com.myCode.termTrain.domain.user.infrastructure;

import com.myCode.termTrain.domain.user.core.model.User;

public interface UserDatabase {
    User saveAndFlush(User user);

    User findByUsername(String username);
}
