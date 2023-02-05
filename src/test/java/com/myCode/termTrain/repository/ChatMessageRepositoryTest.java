package com.myCode.termTrain.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myCode.termTrain.model.ChatMessage;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ChatMessageRepositoryTest {
    
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnListOfAllChatMessagesByForumId(){
       List<ChatMessage> chat = chatMessageRepository.findByForumId(1);
       Assertions.assertEquals(chat.size(), 2);
    }
}
