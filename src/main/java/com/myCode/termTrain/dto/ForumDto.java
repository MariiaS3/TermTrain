package com.myCode.termTrain.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.myCode.termTrain.model.ChatMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForumDto {

    private Integer id;

    private String username;
    private String title;

    private List<ChatMessage> chatMessages = new ArrayList<>();

}
