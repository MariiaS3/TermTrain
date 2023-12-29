package com.myCode.termTrain.controller;

import java.util.ArrayList;
import java.util.List;

import com.myCode.termTrain.domain.file.aplication.command.FileCommandController;
import com.myCode.termTrain.domain.file.core.model.File;
import com.myCode.termTrain.domain.file.core.service.command.FileCommandService;
import com.myCode.termTrain.domain.file.aplication.query.FileQueryController;
import com.myCode.termTrain.domain.file.core.service.query.FileQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.myCode.termTrain.domain.file.core.dto.FileDto;
import com.myCode.termTrain.domain.file.core.dto.RequestFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FileControllerTest {
    
    @InjectMocks
    private FileCommandController fileCommandController;

    @InjectMocks
    private FileQueryController fileQueryController;

    @Mock
    private FileCommandService fileCommandService;
    @Mock
    private FileQueryService fileQueryService;

    @Test
    void shouldReturnFileWhenGetFileByNameColled(){
        List<FileDto> fileDtos = new ArrayList<>();
        fileDtos.add(getFileDto());
        when(fileQueryService.getFileByName(anyString())).thenReturn(fileDtos);
        ResponseEntity<List<FileDto>> dEntity = fileQueryController.getFileByName("test.txt");
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().size()).isEqualTo(1);
        assertThat(dEntity.getBody().get(0).getId()).isEqualTo(1);
        assertThat(dEntity.getBody().get(0).getPath()).isEqualTo("/testdir");
        assertThat(dEntity.getBody().get(0).getName()).isEqualTo("test.txt");
        assertThat(dEntity.getBody().get(0).getLink()).isEqualTo(1);
        assertThat(dEntity.getBody().get(0).getPermisions()).isEqualTo("-rw-r--r--");
        assertThat(dEntity.getBody().get(0).getUsername()).isEqualTo("root");
        assertThat(dEntity.getBody().get(0).getGroupname()).isEqualTo("group");
        assertThat(dEntity.getBody().get(0).getIsDirectory()).isEqualTo(false);
        assertThat(dEntity.getBody().get(0).getSize()).isEqualTo(4096);
        assertThat(dEntity.getBody().get(0).getText()).isEqualTo("some string");
        assertThat(dEntity.getBody().get(0).getTime()).isEqualTo("Aug 7 10:51");
    }

    @Test
    void shouldRetornDirOrFileWhenGetDirOrFileByPathColled(){
        List<FileDto> dirorfileDtos = new ArrayList<>();
        dirorfileDtos.add(getDirDto());
        when(fileQueryService.getFileByPath(anyString())).thenReturn(dirorfileDtos);
        ResponseEntity<List<FileDto>> dEntity = fileQueryController.getFileByPath("-testfolder");
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().size()).isEqualTo(1);
        assertThat(dEntity.getBody().get(0).getId()).isEqualTo(1);
        assertThat(dEntity.getBody().get(0).getPath()).isEqualTo("/testfolder");
        assertThat(dEntity.getBody().get(0).getName()).isEqualTo("testdir");
        assertThat(dEntity.getBody().get(0).getLink()).isEqualTo(2);
        assertThat(dEntity.getBody().get(0).getPermisions()).isEqualTo("drwxr-xr-x");
        assertThat(dEntity.getBody().get(0).getUsername()).isEqualTo("root");
        assertThat(dEntity.getBody().get(0).getGroupname()).isEqualTo("group");
        assertThat(dEntity.getBody().get(0).getIsDirectory()).isEqualTo(true);
        assertThat(dEntity.getBody().get(0).getSize()).isEqualTo(4096);
        assertThat(dEntity.getBody().get(0).getText()).isEqualTo("");
        assertThat(dEntity.getBody().get(0).getTime()).isEqualTo("Aug 7 10:51");
    }

    @Test
    void shouldRetornDirOrFileWhenGetDirOrFileByNameAndPathColled(){
        FileDto fileDto = getFileDto();
        when(fileQueryService.getFileByPathAndName(any(RequestFile.class))).thenReturn(fileDto);
        
        RequestFile file =  new RequestFile("test.txt","/api/v1/path-name/-testdir-");
        ResponseEntity<FileDto> dEntity = fileQueryController.getFileByNameAndPath(file);
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().getId()).isEqualTo(1);
        assertThat(dEntity.getBody().getPath()).isEqualTo("/testdir");
        assertThat(dEntity.getBody().getName()).isEqualTo("test.txt");
        assertThat(dEntity.getBody().getLink()).isEqualTo(1);
        assertThat(dEntity.getBody().getPermisions()).isEqualTo("-rw-r--r--");
        assertThat(dEntity.getBody().getUsername()).isEqualTo("root");
        assertThat(dEntity.getBody().getGroupname()).isEqualTo("group");
        assertThat(dEntity.getBody().getIsDirectory()).isEqualTo(false);
        assertThat(dEntity.getBody().getSize()).isEqualTo(4096);
        assertThat(dEntity.getBody().getText()).isEqualTo("some string");
        assertThat(dEntity.getBody().getTime()).isEqualTo("Aug 7 10:51");

    }

    @Test
    void shouldCreateFileWhenCreateNewFieColled(){
        File file = getFile();

        when(fileCommandService.createNewFile(any(File.class))).thenReturn(file.toFileDto());

        ResponseEntity<FileDto> dEntity = fileCommandController.createNewFile(file);
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().getId()).isEqualTo(1);
        assertThat(dEntity.getBody().getPath()).isEqualTo("/testdir");
        assertThat(dEntity.getBody().getName()).isEqualTo("test.txt");
        assertThat(dEntity.getBody().getLink()).isEqualTo(1);
        assertThat(dEntity.getBody().getPermisions()).isEqualTo("-rw-r--r--");
        assertThat(dEntity.getBody().getUsername()).isEqualTo("root");
        assertThat(dEntity.getBody().getGroupname()).isEqualTo("group");
        assertThat(dEntity.getBody().getIsDirectory()).isEqualTo(false);
        assertThat(dEntity.getBody().getSize()).isEqualTo(4096);
        assertThat(dEntity.getBody().getText()).isEqualTo("some string");
        assertThat(dEntity.getBody().getTime()).isEqualTo("Aug 7 10:51");
    }

    @Test
    void shouldUpdateFileWhenUpdateFieColled(){
        File file = getFile();
        file.setText("some some string");
        when(fileCommandService.updateFile(anyString(), any(File.class))).thenReturn(file.toFileDto());

        ResponseEntity<FileDto> dEntity = fileCommandController.updateFile(String.valueOf(file.getId()), file);
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().getId()).isEqualTo(1);
        assertThat(dEntity.getBody().getPath()).isEqualTo("/testdir");
        assertThat(dEntity.getBody().getName()).isEqualTo("test.txt");
        assertThat(dEntity.getBody().getLink()).isEqualTo(1);
        assertThat(dEntity.getBody().getPermisions()).isEqualTo("-rw-r--r--");
        assertThat(dEntity.getBody().getUsername()).isEqualTo("root");
        assertThat(dEntity.getBody().getGroupname()).isEqualTo("group");
        assertThat(dEntity.getBody().getIsDirectory()).isEqualTo(false);
        assertThat(dEntity.getBody().getSize()).isEqualTo(4096);
        assertThat(dEntity.getBody().getText()).isEqualTo("some some string");
        assertThat(dEntity.getBody().getTime()).isEqualTo("Aug 7 10:51");
    }

    private FileDto getDirDto(){
        return FileDto.builder()
                .id(1)
                .path("/testfolder")
                .name("testdir")
                .link(2)
                .permisions("drwxr-xr-x")
                .username("root")
                .groupname("group")
                .isDirectory(true)
                .size(4096)
                .text("")
                .time("Aug 7 10:51")
                .build();
    }

    private FileDto getFileDto(){
        return FileDto.builder()
                .id(1)
                .path("/testdir")
                .name("test.txt")
                .link(1)
                .permisions("-rw-r--r--")
                .username("root")
                .groupname("group")
                .isDirectory(false)
                .size(4096)
                .text("some string")
                .time("Aug 7 10:51")
                .build();
    }

    private File getFile(){
        return File.builder()
                .id(1)
                .path("/testdir")
                .name("test.txt")
                .link(1)
                .permisions("-rw-r--r--")
                .username("root")
                .groupname("group")
                .isDirectory(false)
                .size(4096)
                .text("some string")
                .time("Aug 7 10:51")
                .build();
    }
}
