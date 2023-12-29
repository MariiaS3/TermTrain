package com.myCode.termTrain.domain.forum.infrastructure.item;

import com.myCode.termTrain.domain.forum.core.model.Item;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ItemDatabaseAdapter implements ItemDatabase {
    private final ItemRepository messageRepository;

    @Override
    public List<Item> findAllByForumId(Integer id) {
        return messageRepository.findByForumId(id);
    }

    @Override
    public Item saveAndFlush(Item item) {
        return messageRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Item item) {
        messageRepository.delete(item);
    }
}
