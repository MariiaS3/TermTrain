package com.myCode.termTrain.repository;

import java.util.List;

import com.myCode.termTrain.domain.file.infrastructure.FileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myCode.termTrain.domain.file.core.model.File;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FileRepositoryTest {

    @Autowired
    private FileRepository dirFileRepository;


    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsOrFilesByName(){
        List<File> dirsbyname = dirFileRepository.findByName("testdir");
        List<File> filesbyname = dirFileRepository.findByName("testfile.txt");
        Assertions.assertEquals(dirsbyname.size(), 1);
        Assertions.assertEquals(filesbyname.size(), 1);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsAndFilesByPath(){
        List<File> dirsAndfiles = dirFileRepository.findByPath("/testfolder");

        Assertions.assertEquals(dirsAndfiles.size(), 2);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsAndFilesByPathAndName(){
        File dirName = dirFileRepository.findByPathAndName("/testfolder", "testdir");
        File fileName = dirFileRepository.findByPathAndName("/testfolder", "testfile.txt");

        assertThat(dirName).isNotNull();
        assertThat(fileName).isNotNull();

    }
}
