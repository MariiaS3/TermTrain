package com.myCode.termTrain.domain.forum.infrastructure.item;

import com.myCode.termTrain.domain.forum.core.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByForumId(Integer id);
}
