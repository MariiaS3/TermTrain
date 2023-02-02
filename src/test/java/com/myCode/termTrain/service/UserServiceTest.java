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

import com.myCode.termTrain.dto.UserDto;
import com.myCode.termTrain.model.User;
import com.myCode.termTrain.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

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

    private User getUser(UUID id){
        return User.builder()
                .password("password")
                .id(id)
                .name("username")
                .email("example@gmail.com")
                .build();
    }

    private UserDto getUserDto(){
        return UserDto.builder()
                .password("password")
                .id(UUID.randomUUID())
                .name("username")
                .email("example@gmail.com")
                .build();
    }

    @Test
    void shouldReturnUserWhenEmailIsExist() {
        UUID id = UUID.randomUUID();

        when(userRepository.findByEmail(anyString())).thenReturn(getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(getUserDto());

        UserDto userDto = userService.getUserByEmail("example@gmail.com");

        assertThat(userDto).isNotNull();
        assertThat(userDto.getName()).isEqualTo("username");
    }

    @Test
    void shouldThrowErrorWhenEmailIsNotExist() {
        
        when(userRepository.findByEmail(anyString())).thenThrow( new RuntimeException("error"));

        assertThatThrownBy(() -> 
        userService.getUserByEmail("example@gmail.com")).isInstanceOf(RuntimeException.class);

    }
}


