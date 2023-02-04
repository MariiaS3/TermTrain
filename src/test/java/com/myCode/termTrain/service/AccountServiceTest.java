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

// import com.myCode.termTrain.dto.UserDto;
// import com.myCode.termTrain.model.Account;
// import com.myCode.termTrain.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    // @InjectMocks
    // private AccountService userService;

    // @Mock
    // private AccountRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void sholdReturnUserIdWhenCalledToAddUser(){
        // Integer id = UUID.randomUUID();

        // when(userRepository.saveAndFlush(any())).thenReturn(getUser());
        // when(modelMapper.map(any(), any())).thenReturn(getUser());

        // Integer uuid = userService.addUser(getUserDto());

        // assertThat(uuid).isNotNull();
        // assertThat(uuid).isEqualTo(1);
    }

    // private Account getUser(){
    //     return Account.builder()
    //             .password("password")
    //             .id(1)
    //             .name("username")
    //             .email("example@gmail.com")
    //             .build();
    // }

    // private UserDto getUserDto(){
    //     return UserDto.builder()
    //             .password("password")
    //             .id(1)
    //             .name("username")
    //             .email("example@gmail.com")
    //             .build();
    // }

    @Test
    void shouldReturnUserWhenEmailIsExist() {
        // UUID id = UUID.randomUUID();

        // when(userRepository.findByEmail(anyString())).thenReturn(getUser());
        // when(modelMapper.map(any(), any())).thenReturn(getUserDto());

        // UserDto userDto = userService.getUserByEmail("example@gmail.com");

        // assertThat(userDto).isNotNull();
        // assertThat(userDto.getName()).isEqualTo("username");
    }

    @Test
    void shouldThrowErrorWhenEmailIsNotExist() {
        
    //     when(userRepository.findByEmail(anyString())).thenThrow( new RuntimeException("error"));

    //     assertThatThrownBy(() -> 
    //     userService.getUserByEmail("example@gmail.com")).isInstanceOf(RuntimeException.class);

    }
}


