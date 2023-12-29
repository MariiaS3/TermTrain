package com.myCode.termTrain.domain.forum.core.service.query;

import com.myCode.termTrain.domain.forum.core.ItemAction;
import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemQueryService {
    private final ItemAction messageAction;

    public List<ItemDto> findAllItemsByForumId(Integer id) {
        return messageAction.findAllItemsByForumId(id);
    }
}
