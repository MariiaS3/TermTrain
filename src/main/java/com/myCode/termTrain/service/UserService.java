package com.myCode.termTrain.service;

import java.util.Objects;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myCode.termTrain.dto.UserDto;
import com.myCode.termTrain.model.User;
import com.myCode.termTrain.repository.UserRepository;

@Service
public class UserService {
    
    private final  UserRepository userRepository;
    private final  PasswordEncoder passwordEncoder;
    private final  ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public UUID addUser(UserDto userDto){

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setId(null);

        User createUser = userRepository.saveAndFlush(user);

        return createUser.getId();
    }

    public UserDto getUserByEmail(String email){
        User byEmail = userRepository.findByEmail(email);

        if(Objects.isNull(byEmail)){
            throw new RuntimeException("user not exist with this email: "+email);
        }
        return modelMapper.map(byEmail, UserDto.class);
    }
}
