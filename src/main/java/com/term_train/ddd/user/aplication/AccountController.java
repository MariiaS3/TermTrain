package com.term_train.ddd.user.aplication;

import com.term_train.ddd.user.domain.AccountFacade;
import com.term_train.ddd.user.domain.dto.AccountDto;
import com.term_train.ddd.user.domain.dto.AuthenticationRequest;
import com.term_train.ddd.user.domain.dto.AuthenticationResponse;
import com.term_train.ddd.user.domain.service.AccountService;
import com.term_train.infrastructure.config.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AccountFacade userService;

    public AccountController(AuthenticationManager authenticationManager,
                             JwtUtil jwtUtil, AccountFacade userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

//    public AccountController(AccountCommandService userService) {
//        this.userCommandService = userService;
//    }

    @ApiOperation(value = "User register", response = UUID.class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully created"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 400, message = "This username already exist")

    })
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody AccountDto userDto) {

        try {
            String id = userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("This username already exist");
        }
    }


    @ApiOperation(value = "User login", response = AuthenticationResponse.class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login succesful"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 400, message = "Username or password is incorect")

    })
    @PostMapping("/login")
    public ResponseEntity<?> accountAuthentication(@RequestBody AuthenticationRequest request) {
        String token = "";
        try {
            AccountDto userDetails = userService.verifyUserByUsername(request.getUsername());
            token = jwtUtil.generateToken(userDetails);
            LOG.info("Token " + token);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("username or password is incorect" + request.getUsername() + "   " + request.getPassword());
        }

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
