package com.term_train.ddd.forum.domain;

import com.term_train.ddd.forum.domain.dto.ItemDto;
import com.term_train.ddd.forum.domain.model.Item;
import com.term_train.ddd.forum.infrastructure.adapter.ItemDatabaseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemFacade {

    private final ItemDatabaseAdapter messageDatabase;

    private ItemDto convertMessageToDto(Item message) {
        return message.toItemDto();
    }

    public List<ItemDto> findAllItemsByForumId(Integer id) {
        List<Item> chatMessages = messageDatabase.findAllByForumId(id);
        return chatMessages.stream().map(this::convertMessageToDto).collect(Collectors.toList());
    }

    public ItemDto createNewItem(Item item) {
        Item chatMsg = messageDatabase.saveAndFlush(item);
        return chatMsg.toItemDto();
    }

    public void deleteItem(Item item) {
        messageDatabase.delete(item);
    }
}
