package com.myCode.termTrain.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private Integer id;

    @NotNull
    private String name;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
