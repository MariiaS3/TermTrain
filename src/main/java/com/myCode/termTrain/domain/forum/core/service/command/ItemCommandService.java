package com.myCode.termTrain.domain.forum.core.service.command;

import com.myCode.termTrain.domain.forum.core.ItemAction;
import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import com.myCode.termTrain.domain.forum.core.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCommandService {
    private final ItemAction messageAction;

    public ItemDto createNewItem(Item item) {
        return messageAction.createNewItem(item);
    }

    public void deleteItem(Item item) {
        messageAction.deleteItem(item);
    }
}
