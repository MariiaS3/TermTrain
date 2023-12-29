package com.myCode.termTrain.domain.user.core;

import com.myCode.termTrain.domain.user.core.dto.UserDto;
import com.myCode.termTrain.domain.user.core.model.User;
import com.myCode.termTrain.domain.user.infrastructure.UserDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

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
