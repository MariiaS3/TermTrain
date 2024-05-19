package com.term_train.domain.user.core;

import com.term_train.domain.user.core.dto.UserDto;
import com.term_train.domain.user.core.model.User;
import com.term_train.domain.user.infrastructure.UserDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserFacade implements UserAction {

    private final UserDatabase userDatabase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createUser(UserDto userDto) {
        User user = userDatabase.findByUsername(userDto.getUsername());
        if (user != null) {
            throw new RuntimeException("This username already exist");
        }
        User account = userDto.toUser();
        account.changePassword(passwordEncoder.encode(userDto.getPassword()));
        return userDatabase.saveAndFlush(account).getId().toString();
    }

    @Override
    public UserDto verifyUserByUsername(String email) {
        User account = userDatabase.findByUsername(email);
        if (Objects.isNull(account)) {
            throw new RuntimeException("user not exist with this email: " + email);
        }
        return account.toUserDTO();
    }
}
