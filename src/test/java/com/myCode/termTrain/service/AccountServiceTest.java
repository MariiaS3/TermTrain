package com.myCode.termTrain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.myCode.termTrain.dto.AccountDto;
import com.myCode.termTrain.model.Account;
import com.myCode.termTrain.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    @InjectMocks
    private AccountService userService;

    @Mock
    private AccountRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void sholdReturnUserIdWhenCalledToAddUser(){
        UUID id = UUID.randomUUID();

        when(userRepository.saveAndFlush(any())).thenReturn(getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(getUser(id));

        UUID uuid = userService.addUser(getUserDto());

        assertThat(uuid).isNotNull();
        assertThat(uuid).isEqualTo(id);
    }

    private Account getUser(UUID id){
        return Account.builder()
                .password("password")
                .id(id)
                .name("username")
                .username("example@gmail.com")
                .build();
    }

    private AccountDto getUserDto(){
        return AccountDto.builder()
                .password("password")
                .id(UUID.randomUUID())
                .name("username")
                .username("example@gmail.com")
                .build();
    }

    @Test
    void shouldReturnUserWhenEmailIsExist() {
        UUID id = UUID.randomUUID();

        when(userRepository.findByUsername(anyString())).thenReturn(getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(getUserDto());

        AccountDto userDto = userService.getUserByUsername("example@gmail.com");

        assertThat(userDto).isNotNull();
        assertThat(userDto.getName()).isEqualTo("username");
    }

    @Test
    void shouldThrowErrorWhenEmailIsNotExist() {
        
        when(userRepository.findByUsername(anyString())).thenThrow( new RuntimeException("error"));

        assertThatThrownBy(() -> 
        userService.getUserByUsername("example@gmail.com")).isInstanceOf(RuntimeException.class);

    }
}


