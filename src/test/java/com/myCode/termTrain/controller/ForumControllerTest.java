package com.myCode.termTrain.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.myCode.termTrain.domain.forum.aplication.command.ForumCommandController;
import com.myCode.termTrain.domain.forum.core.model.Item;
import com.myCode.termTrain.domain.forum.core.service.command.ForumCommandService;
import com.myCode.termTrain.domain.forum.aplication.query.ForumQueryController;
import com.myCode.termTrain.domain.forum.core.service.command.ItemCommandService;
import com.myCode.termTrain.domain.forum.core.service.query.ForumQueryService;
import com.myCode.termTrain.domain.forum.core.service.query.ItemQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import com.myCode.termTrain.domain.forum.core.dto.ForumDto;
import com.myCode.termTrain.domain.forum.core.model.Forum;

@ExtendWith(MockitoExtension.class)
public class ForumControllerTest {
    
    @InjectMocks
    private ForumCommandController forumCommandController;
    @InjectMocks
    private ForumQueryController forumQueryController;
    @Mock
    private ForumCommandService forumCommandService;
    @Mock
    private ForumQueryService forumQueryService;
    @Mock
    private ItemQueryService itemQueryService;
    @Mock
    private ItemCommandService itemCommandService;


    @Test
    void shouldReturnForumDtoListWhenGetForumCalled(){
        List<ForumDto> forumDtos = new ArrayList<>();
        forumDtos.add(getForumDto());
        when(forumQueryService.findAllForums()).thenReturn(forumDtos);

        ResponseEntity<List<ForumDto>> listOfForum = forumQueryController.getListOfForum();
        assertThat(listOfForum.getBody()).isNotNull();
        assertThat(listOfForum.getBody().size()).isEqualTo(1);
    }

    @Test
    void shouldReturnItemDtoListWhenGetChatMessangeCalled(){
        List<ItemDto> chatDtos = new ArrayList<>();
        Forum forum = getForum();
        chatDtos.add(getItemDto(forum));

        when(itemQueryService.findAllItemsByForumId(any())).thenReturn(chatDtos);

        ResponseEntity<?> fEntity = forumQueryController.getListOfItems(1);
        assertThat(fEntity.getBody()).isNotNull();
        assertThat(fEntity.getBody()).isEqualTo(chatDtos);
    }

    @Test
    void shouldCreateNewForum(){
        Forum forum = getForum();
        when(forumCommandService.createNewForum(any(Forum.class))).thenReturn(forum.toForumDto());

        ResponseEntity<Integer> id = forumCommandController.createNewForum(forum);
        assertThat(id.getBody()).isNotNull();
        assertThat(id.getBody()).isEqualTo(1);
    }

    @Test
    void shouldCreateNewItem(){
        Forum forum = getForum();
        Item item = getItem(forum);

        when(itemCommandService.createNewItem(item)).thenReturn(item.toItemDto());

        ResponseEntity<Integer> id = forumCommandController.createNewItem(item);
        assertThat(id.getBody()).isNotNull();
        assertThat(id.getBody()).isEqualTo(1);
    }

    private ForumDto getForumDto(){
        return ForumDto.builder().id(1).username("test@gmail.com").title("test forumitem").build();
    }

    private Forum getForum(){
        return Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
    }

    private ItemDto getItemDto(Forum forum){
        return ItemDto.builder().id(1).username("test2@gmail.com").message("jakas wiadomosc 1").forum(forum).build();
    }

    private Item getItem(Forum forum){
        return Item.builder().id(1).username("test2@gmail.com").message("jakas wiadomosc 1").forum(forum).build();
    }
}
