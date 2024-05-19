package com.term_train.domain.user.infrastructure;

import com.term_train.domain.user.core.model.User;

public interface UserDatabase {
    User saveAndFlush(User user);

    User findByUsername(String username);
}
