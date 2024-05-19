package com.term_train.domain.forum.core;

import com.term_train.domain.forum.core.dto.ItemDto;
import com.term_train.domain.forum.core.model.Item;

import java.util.List;

public interface ItemAction {
    List<ItemDto> findAllItemsByForumId(Integer id);

    ItemDto createNewItem(Item item);

    void deleteItem(Item item);
}
