package com.myCode.termTrain.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myCode.termTrain.dto.AccountDto;

@Service
public class AccountDetailService implements UserDetailsService {
    
    private final AccountService accountService;

    public AccountDetailService(AccountService accountService) {
        this.accountService = accountService;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto user =  accountService.getUserByUsername(username);
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }




}
