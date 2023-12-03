package com.termtrain.userms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
//@ApiModel(value = "AccountDto", description = "all details about Users")
public class AccountDto {

//    @ApiModelProperty(readOnly = true, value = "UUID", dataType = "UUID", example = "b5607d38-8fc1-43ef-b44e-34967083c80", notes = "The database generated uuid for user id", required = true)
    private UUID id;

//    @ApiModelProperty( value = "name", dataType = "String", example = "UserName", notes = "User name", required = true)
    private String name;

//    @ApiModelProperty( value = "email", dataType = "String", example = "user@gmail.com", notes = "User email", required = true)
    private String username;

//    @ApiModelProperty( value = "password", dataType = "String", example = "password", notes = "User password", required = true)
    private String password;
}
