package com.term_train.domain.forum.infrastructure.item;

import com.term_train.domain.forum.core.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByForumId(Integer id);
}
