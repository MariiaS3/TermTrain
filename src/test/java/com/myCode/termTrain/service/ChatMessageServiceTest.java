package com.myCode.termTrain.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.myCode.termTrain.dto.ChatMessageDto;
import com.myCode.termTrain.model.ChatMessage;
import com.myCode.termTrain.model.Forum;
import com.myCode.termTrain.repository.ChatMessageRepository;

@ExtendWith(MockitoExtension.class)
public class ChatMessageServiceTest {
    
    @InjectMocks
    private ChatMessageService chatMessageService;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void shouldReturnListOfAllChatMessagesByForumId(){
        List<ChatMessage> chatMessages = new ArrayList<>();
        ChatMessage chat = getChat();
        chatMessages.add(getChat());
        ChatMessageDto chatMessageDto = getChatDto();

        when(chatMessageRepository.findByForumId(anyInt())).thenReturn(chatMessages);
        when(modelMapper.map(chat, ChatMessageDto.class)).thenReturn(chatMessageDto);

        List<ChatMessageDto> chatMessageDtos = chatMessageService.findByForumId(1);
        assertThat(chatMessageDtos.size()).isEqualTo(1);

    }

    private ChatMessage getChat(){
        Forum forum = Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
        return ChatMessage.builder().id(1).username("test2@gmail.com").message("jakas wiadomosc 1").forum(forum).build();
    }

    private ChatMessageDto getChatDto(){
        Forum forum = Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
        return ChatMessageDto.builder().id(1).username("test2@gmail.com").message("jakas wiadomosc 1").forum(forum).build();
    }
}
