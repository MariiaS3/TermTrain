package com.term_train.infrastructure.util;

import com.term_train.domain.user.core.dto.AccountDto;
import com.term_train.domain.user.core.service.command.AccountCommandService;
import com.term_train.domain.user.core.service.query.AccountQueryService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountDetailService implements UserDetailsService {

    private final AccountCommandService userCommandService;
    private final AccountQueryService userQueryService;


    public AccountDetailService(AccountCommandService userCommandService, AccountQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto user =  userQueryService.verifyUserByUsername(username);
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }

    public void addUser(String username) throws UsernameNotFoundException {
        AccountDto userDto = new AccountDto();
        userDto.setName("user");
        userDto.setPassword("password");
        userDto.setUsername(username);

        String id = userCommandService.createUser(userDto);
    }
}