package com.termtrain.authutils.controller;

import java.util.UUID;

import com.termtrain.authutils.dto.AccountDto;
import com.termtrain.authutils.dto.AuthenticationRequest;
import com.termtrain.authutils.dto.AuthenticationResponse;
import com.termtrain.authutils.service.AccountDetailService;
import com.termtrain.authutils.service.AccountService;
import com.termtrain.authutils.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


//@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private final AccountDetailService userDetailService;
    private final JwtService jwtService;
    private final AccountService userService;



    public AuthController(AuthenticationManager authenticationManager, AccountDetailService userDetailService,
                          JwtService jwtUtil, AccountService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtService = jwtUtil;
        this.userService = userService;
    }

//    @ApiOperation(value = "User login", response =  AuthenticationResponse.class, produces = "aplication/json")  //about this endpoint
//    @ApiResponses(value = {
//        @ApiResponse(code = 200 , message = "Login succesful"),
//        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
//        @ApiResponse(code = 400, message = "Username or password is incorect")
//
//    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){

        String token ="";
        String refreshToken = "";

        try{
            UserDetails userdetails =  userDetailService.loadUserByUsername(request.getUsername());
            token = jwtService.generate(userdetails, "ACCESS");
            refreshToken = jwtService.generate(userdetails, "REFRESH");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("username or password is incorect");
        }

        return ResponseEntity.ok(new AuthenticationResponse("Bearer " + token, refreshToken));
    }

//    @ApiOperation(value = "User register", response = UUID.class, produces = "aplication/json")  //about this endpoint
//    @ApiResponses(value = {
//        @ApiResponse(code = 201 , message = "Succesfully created"),
//        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
//        @ApiResponse(code = 400, message = "This username already exist")
//
//    })
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

    @GetMapping("/test")
    public String test(){
        System.out.println("__________________________________ test -------------------------------------------------------");
        return "Hello JavaInUse Called in Second Service";
    }
}
