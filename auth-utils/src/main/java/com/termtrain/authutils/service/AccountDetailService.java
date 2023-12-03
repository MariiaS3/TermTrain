package com.termtrain.authutils.service;

import com.termtrain.authutils.dto.AccountDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountDetailService implements UserDetailsService {
    
    private final AccountService accountService;

    public AccountDetailService(AccountService accountService) {
        this.accountService = accountService;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("__________________________________ loaduserbyname -------------------------------------------------------");
        AccountDto user =  accountService.getUserByUsername(username);
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }


}
