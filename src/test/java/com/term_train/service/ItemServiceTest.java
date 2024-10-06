package com.term_train.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.term_train.ddd.forum.domain.ItemFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.term_train.ddd.forum.domain.dto.ItemDto;
import com.term_train.ddd.forum.domain.model.Item;
import com.term_train.ddd.forum.domain.model.Forum;
import com.term_train.ddd.forum.infrastructure.port.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    
    @InjectMocks
    private ItemFacade itemFacade;

    @Mock
    private ItemRepository itemRepository;

    @Test
    void shouldReturnAllItems(){
        List<Item> chatMessages = new ArrayList<>();
        Item chat = getItem();
        chatMessages.add(getItem());
        ItemDto chatMessageDto = getItemDto();

        when(itemRepository.findByForumId(anyInt())).thenReturn(chatMessages);
        when(chat.toItemDto()).thenReturn(chatMessageDto);

        List<ItemDto> itemDtos = itemFacade.findAllItemsByForumId(1);
        assertThat(itemDtos.size()).isEqualTo(1);

    }

    @Test
    void shouldCreateNewItem(){
        Item item = getItem();

        when(itemRepository.saveAndFlush(item)).thenReturn(item);

        ItemDto itemDto = itemFacade.createNewItem(item);
        assertThat(itemDto.getId()).isEqualTo(1);
        assertThat(itemDto.getUsername()).isEqualTo("test2@gmail.com");
        assertThat(itemDto.getMessage()).isEqualTo("jakas wiadomosc 1");
    }

    private Item getItem(){
        Forum forum = Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
        return Item.builder().id(1).username("test2@gmail.com").message("jakas wiadomosc 1").forum(forum).build();
    }

    private ItemDto getItemDto(){
        Forum forum = Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
        return ItemDto.builder().id(1).username("test2@gmail.com").message("jakas wiadomosc 1").forum(forum).build();
    }
}
