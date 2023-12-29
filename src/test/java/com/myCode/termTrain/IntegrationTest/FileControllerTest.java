package com.myCode.termTrain.IntegrationTest;

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
import com.myCode.termTrain.domain.file.core.dto.FileDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

@SpringBootTest(classes = TermTrainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FileControllerTest {
    
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
            "name","username", passwordEncoder.encode("password"), "test@gmail.com"
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
        FileDto[] listofdirsAndfiles = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/name/testdir", FileDto[].class);
        assertThat(listofdirsAndfiles).isNotNull();
        assertThat(listofdirsAndfiles.length).isEqualTo(1);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirOrFileWhenGetDirOrFileByPathColled(){
        FileDto[] listofdirsAndfiles = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/path/-testfolder", FileDto[].class);
        assertThat(listofdirsAndfiles).isNotNull();
        assertThat(listofdirsAndfiles.length).isEqualTo(2);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirOrFileWhenGetDirOrFileByNameAndPathColled(){
        
    }
}
