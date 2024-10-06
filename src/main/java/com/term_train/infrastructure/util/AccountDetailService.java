package com.term_train.infrastructure.util;

import com.term_train.ddd.user.domain.AccountFacade;
import com.term_train.ddd.user.domain.dto.AccountDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountDetailService implements UserDetailsService {

    private final AccountFacade accountFacade;


    public AccountDetailService(AccountFacade userQueryService) {
        this.accountFacade = userQueryService;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto user =  accountFacade.verifyUserByUsername(username);
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }

    public void addUser(String username) throws UsernameNotFoundException {
        AccountDto userDto = new AccountDto();
        userDto.setName("user");
        userDto.setPassword("password");
        userDto.setUsername(username);

//        String id = accountFacade.createUser(userDto);
    }
}