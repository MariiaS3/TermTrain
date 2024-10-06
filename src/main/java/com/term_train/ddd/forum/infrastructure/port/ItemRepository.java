package com.term_train.ddd.forum.infrastructure.port;

import com.term_train.ddd.forum.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByForumId(Integer id);
}
