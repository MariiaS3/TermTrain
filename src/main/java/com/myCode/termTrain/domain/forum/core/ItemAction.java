package com.myCode.termTrain.domain.forum.core;

import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import com.myCode.termTrain.domain.forum.core.model.Item;

import java.util.List;

public interface ItemAction {
    List<ItemDto> findAllItemsByForumId(Integer id);

    ItemDto createNewItem(Item item);

    void deleteItem(Item item);
}
