package com.term_train.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.term_train.ddd.forum.aplication.ForumController;
import com.term_train.ddd.forum.domain.ForumFacade;
import com.term_train.ddd.forum.domain.ItemFacade;
import com.term_train.ddd.forum.domain.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.term_train.ddd.forum.domain.dto.ItemDto;
import com.term_train.ddd.forum.domain.dto.ForumDto;
import com.term_train.ddd.forum.domain.model.Forum;

@ExtendWith(MockitoExtension.class)
public class ForumControllerTest {
    
    @InjectMocks
    private ForumController forumController;
    @Mock
    private ForumFacade forumFacade;
    @Mock
    private ItemFacade itemFacade;


    @Test
    void shouldReturnForumDtoListWhenGetForumCalled(){
        List<ForumDto> forumDtos = new ArrayList<>();
        forumDtos.add(getForumDto());
        when(forumFacade.findAllForums()).thenReturn(forumDtos);

        ResponseEntity<List<ForumDto>> listOfForum = forumController.getListOfForum();
        assertThat(listOfForum.getBody()).isNotNull();
        assertThat(listOfForum.getBody().size()).isEqualTo(1);
    }

    @Test
    void shouldReturnItemDtoListWhenGetChatMessangeCalled(){
        List<ItemDto> chatDtos = new ArrayList<>();
        Forum forum = getForum();
        chatDtos.add(getItemDto(forum));

        when(itemFacade.findAllItemsByForumId(any())).thenReturn(chatDtos);

        ResponseEntity<?> fEntity = forumController.getListOfItems(1);
        assertThat(fEntity.getBody()).isNotNull();
        assertThat(fEntity.getBody()).isEqualTo(chatDtos);
    }

    @Test
    void shouldCreateNewForum(){
        Forum forum = getForum();
        when(forumFacade.createNewForum(any(Forum.class))).thenReturn(forum.toForumDto());

        ResponseEntity<Integer> id = forumController.createNewForum(forum);
        assertThat(id.getBody()).isNotNull();
        assertThat(id.getBody()).isEqualTo(1);
    }

    @Test
    void shouldCreateNewItem(){
        Forum forum = getForum();
        Item item = getItem(forum);

        when(itemFacade.createNewItem(item)).thenReturn(item.toItemDto());

        ResponseEntity<Integer> id = forumController.createNewItem(item);
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
