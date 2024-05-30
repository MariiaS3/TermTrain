package com.term_train.domain.user.aplication.query;

import com.term_train.domain.user.core.dto.AuthenticationRequest;
import com.term_train.domain.user.core.dto.AuthenticationResponse;
import com.term_train.domain.user.core.dto.AccountDto;
import com.term_train.domain.user.core.service.query.AccountQueryService;
import com.term_train.infrastructure.config.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class AccountQueryController {
    private static final Logger LOG = LoggerFactory.getLogger(AccountQueryController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AccountQueryService userQueryService;

    public AccountQueryController(AuthenticationManager authenticationManager,
                                  JwtUtil jwtUtil, AccountQueryService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userQueryService = userService;
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
            AccountDto userDetails = userQueryService.verifyUserByUsername(request.getUsername());
            token = jwtUtil.generateToken(userDetails);
            LOG.info("Token " + token);
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("username or password is incorect" + request.getUsername() + "   " + request.getPassword());
        }

        return ResponseEntity.ok(new AuthenticationResponse("Bearer " + token));
    }

}
