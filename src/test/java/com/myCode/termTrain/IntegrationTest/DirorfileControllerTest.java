package com.myCode.termTrain.IntegrationTest;

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
import com.myCode.termTrain.dto.DirorfileDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest(classes = TermTrainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DirorfileControllerTest {
    
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
    void shouldReturnDirOrFileWhenGetDirOrFileByNameColled(){
        DirorfileDto[] listofdirsAndfiles = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/name/testdir", DirorfileDto[].class);
        assertThat(listofdirsAndfiles).isNotNull();
        assertThat(listofdirsAndfiles.length).isEqualTo(1);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirOrFileWhenGetDirOrFileByPathColled(){
        DirorfileDto[] listofdirsAndfiles = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/path/-testfolder", DirorfileDto[].class);
        assertThat(listofdirsAndfiles).isNotNull();
        assertThat(listofdirsAndfiles.length).isEqualTo(2);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirOrFileWhenGetDirOrFileByNameAndPathColled(){
        
    }
}
