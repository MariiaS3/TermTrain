package com.myCode.termTrain.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myCode.termTrain.model.Forum;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ForumRepositoryTest {
    
    @Autowired
    private ForumRepository forumRepository;

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnListOfAllForumItems(){
        List<Forum> items = forumRepository.findAll();
        Assertions.assertEquals(items.size(), 1);
    }
}
