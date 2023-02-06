package com.myCode.termTrain.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myCode.termTrain.config.JwtUtil;
import com.myCode.termTrain.dto.AuthenticationRequest;
import com.myCode.termTrain.dto.AuthenticationResponse;
import com.myCode.termTrain.dto.AccountDto;
import com.myCode.termTrain.service.AccountDetailService;
import com.myCode.termTrain.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class AccountController {
    
    private final AuthenticationManager authenticationManager;
    private final AccountDetailService userDetailService;
    private final JwtUtil jwtUtil;
    private final AccountService userService;



    public AccountController(AuthenticationManager authenticationManager, AccountDetailService userDetailService,
            JwtUtil jwtUtil, AccountService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @ApiOperation(value = "User login", response =  AuthenticationResponse.class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 200 , message = "Login succesful"),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 400, message = "Username or password is incorect")

    }) 
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){
        String token ="";
        
        try{
            UserDetails userdetails =  userDetailService.loadUserByUsername(request.getUsername());
            token = jwtUtil.generateToken(userdetails);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("username or password is incorect");
        }
        
        return ResponseEntity.ok(new AuthenticationResponse("Bearer "+token));
    }

    @ApiOperation(value = "User register", response = UUID.class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 201 , message = "Succesfully created"),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 400, message = "This username already exist")

    }) 
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody AccountDto userDto){
  
        try{
            UserDetails user = userDetailService.loadUserByUsername(userDto.getUsername());
            if(user!=null){
                return ResponseEntity.badRequest().body("This username already exist"); 
            }
        }catch(Exception ex){
            
        }
        
        UUID id = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
    
}
