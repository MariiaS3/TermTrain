package com.myCode.termTrain.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.myCode.termTrain.dto.ChatMessageDto;
import com.myCode.termTrain.model.ChatMessage;
import com.myCode.termTrain.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
    
    private final ChatMessageRepository chatMessageRepository;
    private final ModelMapper modelMapper;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, ModelMapper modelMapper) {
        this.chatMessageRepository = chatMessageRepository;
        this.modelMapper = modelMapper;
    }

    private Function<? super ChatMessage, ? extends ChatMessageDto> convertMessageToDto(){
        return message -> modelMapper.map(message, ChatMessageDto.class);
    }

    public List<ChatMessageDto> findByForumId(Integer id){
        List<ChatMessage> chatMessages = chatMessageRepository.findByForumId(id);
        return chatMessages.stream().map(convertMessageToDto()).collect(Collectors.toList());
    }

    public Integer save(ChatMessage  chatMessage){
        ChatMessage chatMsg = chatMessageRepository.saveAndFlush(chatMessage);
        return chatMsg.getId();
    }

    public void remove(ChatMessage  chatMessage){
        chatMessageRepository.delete(chatMessage);
    }
}
