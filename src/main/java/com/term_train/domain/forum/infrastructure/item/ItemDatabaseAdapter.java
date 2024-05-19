package com.term_train.domain.forum.infrastructure.item;

import com.term_train.domain.forum.core.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
