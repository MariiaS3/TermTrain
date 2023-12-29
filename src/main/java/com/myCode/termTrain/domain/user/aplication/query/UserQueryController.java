package com.myCode.termTrain.domain.user.aplication.query;

import com.myCode.termTrain.domain.user.core.dto.AuthenticationRequest;
import com.myCode.termTrain.domain.user.core.dto.AuthenticationResponse;
import com.myCode.termTrain.domain.user.core.dto.UserDto;
import com.myCode.termTrain.domain.user.core.service.query.UserQueryService;
import com.myCode.termTrain.infrastructure.config.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class UserQueryController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserQueryService userQueryService;

    public UserQueryController(AuthenticationManager authenticationManager,
                               JwtUtil jwtUtil, UserQueryService userService) {
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
    public ResponseEntity<?> userAuthentication(@RequestBody AuthenticationRequest request) {
        String token = "";

        try {
            UserDto userdetails = userQueryService.verifyUserByUsername(request.getUsername());
            token = jwtUtil.generateToken(userdetails);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("username or password is incorect");
        }

        return ResponseEntity.ok(new AuthenticationResponse("Bearer " + token));
    }

}
