package com.term_train.ddd.forum.infrastructure.adapter;

import com.term_train.ddd.forum.domain.model.Item;
import com.term_train.ddd.forum.infrastructure.port.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemDatabaseAdapter {
    private final ItemRepository messageRepository;

    public List<Item> findAllByForumId(Integer id) {
        return messageRepository.findByForumId(id);
    }

    public Item saveAndFlush(Item item) {
        return messageRepository.saveAndFlush(item);
    }

    public void delete(Item item) {
        messageRepository.delete(item);
    }
}
