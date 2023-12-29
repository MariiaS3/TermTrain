package com.myCode.termTrain.infrastructure.util;

import com.myCode.termTrain.domain.user.core.dto.UserDto;
import com.myCode.termTrain.domain.user.core.service.command.UserCommandService;
import com.myCode.termTrain.domain.user.core.service.query.UserQueryService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountDetailService implements UserDetailsService {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;


    public AccountDetailService(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user =  userQueryService.verifyUserByUsername(username);
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }

    public void addUser(String username) throws UsernameNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setName("user");
        userDto.setPassword("password");
        userDto.setUsername(username);

        String id = userCommandService.createUser(userDto);
    }
}