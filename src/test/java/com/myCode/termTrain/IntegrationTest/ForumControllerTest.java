package com.myCode.termTrain.IntegrationTest;

import java.util.Collections;

import com.myCode.termTrain.domain.user.core.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import com.myCode.termTrain.TermTrainApplication;
import com.myCode.termTrain.infrastructure.config.JwtUtil;
import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import com.myCode.termTrain.domain.forum.core.dto.ForumDto;

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
        String token = jwtUtil.generateToken(new UserDto(
            "name", "username", passwordEncoder.encode("password"), "test@gmail.com"
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
        ItemDto[] lisChatDtos  = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/forum/1", ItemDto[].class);
        assertThat(lisChatDtos).isNotNull();
        assertThat(lisChatDtos.length).isEqualTo(2);
    }
}
