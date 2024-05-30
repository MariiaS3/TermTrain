package com.term_train.domain.user.aplication.command;

import com.term_train.domain.user.aplication.query.AccountQueryController;
import com.term_train.domain.user.core.dto.AccountDto;
import com.term_train.domain.user.core.service.command.AccountCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class AccountCommandController {
    private static final Logger LOG = LoggerFactory.getLogger(AccountCommandController.class);

    private final AccountCommandService userCommandService;

    public AccountCommandController(AccountCommandService userService) {
        this.userCommandService = userService;
    }

    @ApiOperation(value = "User register", response = UUID.class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully created"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 400, message = "This username already exist")

    })
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody AccountDto userDto) {

        try {
            String id = userCommandService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("This username already exist");
        }
    }
}
