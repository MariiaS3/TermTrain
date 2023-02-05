package com.myCode.termTrain.controller;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.ResponseEntity;

import com.myCode.termTrain.dto.ChatMessageDto;
import com.myCode.termTrain.dto.ForumDto;
import com.myCode.termTrain.model.Forum;
import com.myCode.termTrain.service.ChatMessageService;
import com.myCode.termTrain.service.ForumService;

@ExtendWith(MockitoExtension.class)
public class ForumControllerTest {
    
    @InjectMocks
    private ForumController forumController;

    @Mock
    private ForumService forumService;
    
    @Mock
    private ChatMessageService chatMessageService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void shouldReturnForumDtoListWhenGetForumCalled(){
        List<ForumDto> forumDtos = new ArrayList<>();
        forumDtos.add(getForumDto());
        when(forumService.findAll()).thenReturn(forumDtos);
        ResponseEntity<List<ForumDto>> fEntity = forumController.getForum();
        assertThat(fEntity.getBody()).isNotNull();
        assertThat(fEntity.getBody().size()).isEqualTo(1);
    }
    private ForumDto getForumDto(){
        return ForumDto.builder().id(1).username("test@gmail.com").title("test forumitem").build();
    }

    private ChatMessageDto getChatDto(){
        Forum forum = Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
        return ChatMessageDto.builder().id(1).username("test2@gmail.com").message("jakas wiadomosc 1").forum(forum).build();
    }
    @Test
    void shouldReturnChatMessageDtoListWhenGetChatMessangeCalled(){
        List<ChatMessageDto> chatDtos = new ArrayList<>();
        chatDtos.add(getChatDto());
        when(chatMessageService.findByForumId(any())).thenReturn(chatDtos);
        ResponseEntity<?> fEntity = forumController.getChatMessange(1);
        assertThat(fEntity.getBody()).isNotNull();
        assertThat(fEntity.getBody()).isEqualTo(chatDtos);
    }
}
