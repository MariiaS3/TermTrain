package com.termtrain.authutils.service;

import com.termtrain.authutils.dto.AccountDto;
import com.termtrain.authutils.model.Account;
import com.termtrain.authutils.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AccountService {
    
    private final AccountRepository userRepository;
    private final  PasswordEncoder passwordEncoder;
    private final  ModelMapper modelMapper;

    public AccountService(AccountRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public UUID addUser(AccountDto userDto){

        Account user = modelMapper.map(userDto, Account.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Account createUser = userRepository.saveAndFlush(user);
        return createUser.getId();
    }

    public AccountDto getUserByUsername(String email){
        Account byEmail = userRepository.findByUsername(email);

        if(Objects.isNull(byEmail)){
            throw new RuntimeException("user not exist with this email: "+email);
        }
        return modelMapper.map(byEmail, AccountDto.class);
    }

    // public void delete(User user){
    //     userRepository.delete(user);        
    // }
}
