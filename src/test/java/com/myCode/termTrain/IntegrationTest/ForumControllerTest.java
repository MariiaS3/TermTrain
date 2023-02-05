package com.myCode.termTrain.IntegrationTest;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import com.myCode.termTrain.TermTrainApplication;
import com.myCode.termTrain.config.JwtUtil;
import com.myCode.termTrain.dto.ChatMessageDto;
import com.myCode.termTrain.dto.ForumDto;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TermTrainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode =DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD )
public class ForumControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    //in this function i generate token for test
    @BeforeEach
    void setUpHeader(){
        String token = jwtUtil.generateToken(new User(
            "peter@gmail.com", passwordEncoder.encode("password"), new ArrayList<>()
        ));

        testRestTemplate.getRestTemplate().setInterceptors(
            Collections.singletonList(((request, body,execution) ->{
                request.getHeaders().add("Authorization", "Bearer "+token);
                return execution.execute(request, body);
            }))
        );
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnForumDtoListWhenCalledGetForum(){
        ForumDto[] lisForumDtos  = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/forum", ForumDto[].class);
        assertThat(lisForumDtos).isNotNull();
        assertThat(lisForumDtos.length).isEqualTo(1);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnChatMessageDtoListWhenCalledGetChatMessage(){
        ChatMessageDto[] lisChatDtos  = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/forum/1", ChatMessageDto[].class);
        assertThat(lisChatDtos).isNotNull();
        assertThat(lisChatDtos.length).isEqualTo(2);
    }
}
