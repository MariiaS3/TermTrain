package com.myCode.termTrain.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myCode.termTrain.config.JwtUtil;
import com.myCode.termTrain.dto.AuthenticationRequest;
import com.myCode.termTrain.dto.AuthenticationResponse;
import com.myCode.termTrain.dto.UserDto;
import com.myCode.termTrain.service.UserDetailService;
import com.myCode.termTrain.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtil jwtUtil;
    private final UserService userService;



    public UserController(AuthenticationManager authenticationManager, UserDetailService userDetailService,
            JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch(BadCredentialsException ex){
            throw new RuntimeException("username or password is incorect");
        }

        UserDetails userdetails =  userDetailService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userdetails);

        return ResponseEntity.ok(new AuthenticationResponse("Bearer "+token));
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> addUser(@RequestBody UserDto userDto){

        UUID id = userService.addUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
    
}
