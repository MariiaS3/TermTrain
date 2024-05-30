package com.term_train.repository;

import java.util.List;

import com.term_train.domain.vfs.infrastructure.VFSRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.term_train.domain.vfs.core.model.VFS;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FileRepositoryTest {

    @Autowired
    private VFSRepository dirFileRepository;


    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsOrFilesByName(){
        List<VFS> dirsbyname = dirFileRepository.findByName("testdir");
        List<VFS> filesbyname = dirFileRepository.findByName("testfile.txt");
        Assertions.assertEquals(dirsbyname.size(), 1);
        Assertions.assertEquals(filesbyname.size(), 1);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsAndFilesByPath(){
        List<VFS> dirsAndfiles = dirFileRepository.findByPath("/testfolder");

        Assertions.assertEquals(dirsAndfiles.size(), 2);
    }

    @Test
    @Sql(scripts = {"classpath:Insert_data.sql"})
    void shouldReturnDirsAndFilesByPathAndName(){
        VFS dirName = dirFileRepository.findByPathAndName("/testfolder", "testdir");
        VFS fileName = dirFileRepository.findByPathAndName("/testfolder", "testfile.txt");

        assertThat(dirName).isNotNull();
        assertThat(fileName).isNotNull();

    }
}
