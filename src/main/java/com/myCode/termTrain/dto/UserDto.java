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
public class UserDto {
    private UUID id;

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
