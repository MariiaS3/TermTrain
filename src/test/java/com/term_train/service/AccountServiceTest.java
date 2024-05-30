package com.term_train.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;

import com.term_train.domain.user.core.service.query.UserQueryService;
import com.term_train.domain.user.core.service.command.UserCommandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.term_train.domain.user.core.dto.AccountDto;
import com.term_train.domain.user.core.model.Account;
import com.term_train.domain.user.infrastructure.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    @InjectMocks
    private UserCommandService userCommandService;
    @InjectMocks
    private UserQueryService userQueryService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void sholdReturnUserIdWhenCalledToAddUser(){
        UUID id = UUID.randomUUID();

        when(userRepository.saveAndFlush(any())).thenReturn(getUser(id));

        String uuid = userCommandService.createUser(getUserDto());

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

        AccountDto userDto = userQueryService.verifyUserByUsername("example@gmail.com");

        assertThat(userDto).isNotNull();
        assertThat(userDto.getName()).isEqualTo("username");
    }

    @Test
    void shouldThrowErrorWhenUsernameIsNotExist() {
        
        when(userRepository.findByUsername(anyString())).thenThrow( new RuntimeException("error"));

        assertThatThrownBy(() ->
                userQueryService.verifyUserByUsername("example@gmail.com")).isInstanceOf(RuntimeException.class);

    }
}


