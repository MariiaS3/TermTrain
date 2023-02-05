package com.myCode.termTrain.dto;

import com.myCode.termTrain.model.Forum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {
 
    private Integer id;

    private String username;
    private String message;

    private Forum forum;

}
