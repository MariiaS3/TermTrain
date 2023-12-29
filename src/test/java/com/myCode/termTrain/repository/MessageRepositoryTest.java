package com.myCode.termTrain.repository;

import java.util.List;

import com.myCode.termTrain.domain.forum.infrastructure.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myCode.termTrain.domain.forum.core.model.Item;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MessageRepositoryTest {
    
    @Autowired
    private ItemRepository chatMessageRepository;

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnListOfAllChatMessagesByForumId(){
       List<Item> chat = chatMessageRepository.findByForumId(1);
       Assertions.assertEquals(chat.size(), 2);
    }
}
