package com.myCode.termTrain.domain.user.infrastructure;

import com.myCode.termTrain.domain.user.core.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserDatabase {

    private final UserRepository userRepository;

    @Override
    public User saveAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
