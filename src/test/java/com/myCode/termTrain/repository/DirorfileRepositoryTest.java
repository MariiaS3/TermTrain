package com.myCode.termTrain.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myCode.termTrain.model.Dirorfile;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DirorfileRepositoryTest {

    @Autowired
    private DirorfileRepository dirFileRepository;


    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsOrFilesByName(){
        List<Dirorfile> dirsbyname = dirFileRepository.findByName("testdir");
        List<Dirorfile> filesbyname = dirFileRepository.findByName("testfile.txt");
        Assertions.assertEquals(dirsbyname.size(), 1);
        Assertions.assertEquals(filesbyname.size(), 1);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsAndFilesByPath(){
        List<Dirorfile> dirsAndfiles = dirFileRepository.findByPath("/testfolder");

        Assertions.assertEquals(dirsAndfiles.size(), 2);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsAndFilesByPathAndName(){
        Dirorfile dirName = dirFileRepository.findByPathAndName("/testfolder", "testdir");
        Dirorfile fileName = dirFileRepository.findByPathAndName("/testfolder", "testfile.txt");

        assertThat(dirName).isNotNull();
        assertThat(fileName).isNotNull();

    }
}
