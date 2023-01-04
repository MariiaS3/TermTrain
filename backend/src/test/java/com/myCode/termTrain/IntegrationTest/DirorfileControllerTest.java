package com.myCode.termTrain.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import com.myCode.termTrain.TermTrainApplication;
import com.myCode.termTrain.dto.DirorfileDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TermTrainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DirorfileControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql(scripts = {"classpath:InsertInitialDirorfileRecordForTest.sql"})
    void shouldReturnDirOrFileWhenGetDirOrFileByNameColled(){
        DirorfileDto[] listofdirsAndfiles = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/name/testdir", DirorfileDto[].class);
        assertThat(listofdirsAndfiles).isNotNull();
        assertThat(listofdirsAndfiles.length).isEqualTo(1);
    }

    @Test
    @Sql(scripts = {"classpath:InsertInitialDirorfileRecordForTest.sql"})
    void shouldReturnDirOrFileWhenGetDirOrFileByPathColled(){
        DirorfileDto[] listofdirsAndfiles = testRestTemplate.getForObject("http://localhost:"+port+ "/api/v1/path//testfolder", DirorfileDto[].class);
        assertThat(listofdirsAndfiles).isNotNull();
        assertThat(listofdirsAndfiles.length).isEqualTo(1);
    }

    @Test
    @Sql(scripts = {"classpath:InsertInitialDirorfileRecordForTest.sql"})
    void shouldReturnDirOrFileWhenGetDirOrFileByNameAndPathColled(){
        
    }
}
