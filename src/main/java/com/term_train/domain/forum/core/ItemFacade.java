package com.term_train.domain.forum.core;

import com.term_train.domain.forum.core.dto.ItemDto;
import com.term_train.domain.forum.core.model.Item;
import com.term_train.domain.forum.infrastructure.item.ItemDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemFacade implements ItemAction {

    private final ItemDatabase messageDatabase;

    private ItemDto convertMessageToDto(Item message) {
        return message.toItemDto();
    }

    @Override
    public List<ItemDto> findAllItemsByForumId(Integer id) {
        List<Item> chatMessages = messageDatabase.findAllByForumId(id);
        return chatMessages.stream().map(this::convertMessageToDto).collect(Collectors.toList());
    }

    @Override
    public ItemDto createNewItem(Item item) {
        Item chatMsg = messageDatabase.saveAndFlush(item);
        return chatMsg.toItemDto();
    }

    @Override
    public void deleteItem(Item item) {
        messageDatabase.delete(item);
    }
}
