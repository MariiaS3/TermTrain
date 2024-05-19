package com.term_train.domain.forum.infrastructure.item;

import com.term_train.domain.forum.core.model.Item;

import java.util.List;

public interface ItemDatabase {
    List<Item> findAllByForumId(Integer id);

    Item saveAndFlush(Item item);

    void delete(Item item);
}
