package com.myCode.termTrain.controller;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myCode.termTrain.dto.ChatMessageDto;
import com.myCode.termTrain.dto.ForumDto;
import com.myCode.termTrain.model.ChatMessage;
import com.myCode.termTrain.service.ChatMessageService;
import com.myCode.termTrain.service.ForumService;

@RestController
@RequestMapping("/api/v1")
public class ForumController {
    
    private final ForumService forumService;
    private final ModelMapper modelMapper;
    private final ChatMessageService chatMessageService;

    public ForumController(ForumService forumService, ModelMapper modelMapper, ChatMessageService chatMessageService) {
        this.forumService = forumService;
        this.modelMapper = modelMapper;
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/forum")
    public ResponseEntity<List<ForumDto>> getForum(){
        List<ForumDto> forumDtos = forumService.findAll();
        return ResponseEntity.ok(forumDtos);
    }

    @GetMapping("/forum/{id}")
    public ResponseEntity<List<ChatMessageDto>> getChatMessange(@PathVariable("id") Integer id){
        ForumDto forumDtos = forumService.findById(id);
        List<ChatMessage> messages  =  forumDtos.getChatMessages();
        for (ChatMessage chatMessage : messages) {
            System.out.print(chatMessage.getMessage());
        }
        List<ChatMessage> chatMessages = forumDtos.getChatMessages();
        List<ChatMessageDto> chatMessageDtos = chatMessages.stream().map(convertMessageToDto()).collect(Collectors.toList());

        return ResponseEntity.ok(chatMessageDtos);
    }

    private Function<? super ChatMessage, ? extends ChatMessageDto> convertMessageToDto(){
        return message -> modelMapper.map(message, ChatMessageDto.class);
    }

    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDto sendMessage(@Payload ChatMessage chatMessage){
        chatMessageService.save(chatMessage);
        return modelMapper.map(chatMessage, ChatMessageDto.class);
    }   
}
