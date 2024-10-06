package com.term_train.controller;

import java.util.ArrayList;
import java.util.List;

import com.term_train.ddd.vfs.application.VFSCommandController;
import com.term_train.ddd.vfs.domain.VFSFacade;
import com.term_train.ddd.vfs.domain.model.VFS;
import com.term_train.ddd.vfs.infrastructure.service.FileCommandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.term_train.ddd.vfs.domain.dto.VFSDto;
import com.term_train.ddd.vfs.domain.dto.RequestFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FileControllerTest {
    
    @InjectMocks
    private VFSCommandController fileCommandController;

    @InjectMocks
    private VFSCommandController fileQueryController;

    @Mock
    private FileCommandService fileCommandService;
    @Mock
    private VFSFacade fileQueryService;

    @Test
    void shouldReturnFileWhenGetFileByNameColled(){
        List<VFSDto> fileDtos = new ArrayList<>();
        fileDtos.add(getFileDto());
        when(fileQueryService.findFileByName(anyString())).thenReturn(fileDtos);
        ResponseEntity<List<VFSDto>> dEntity = fileQueryController.getFileByName("test.txt");
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
        List<VFSDto> dirorfileDtos = new ArrayList<>();
        dirorfileDtos.add(getDirDto());
        when(fileQueryService.findFileByPath(anyString())).thenReturn(dirorfileDtos);
        ResponseEntity<List<VFSDto>> dEntity = fileQueryController.getFileByPath("-testfolder");
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
        VFSDto fileDto = getFileDto();
        when(fileQueryService.findFileByPathAndName(any(RequestFile.class))).thenReturn(fileDto);
        
        RequestFile file =  new RequestFile("test.txt","/api/v1/path-name/-testdir-");
        ResponseEntity<VFSDto> dEntity = fileQueryController.getFileByNameAndPath(file);
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
        VFS file = getFile();

        when(fileQueryController.createNewFile(any(VFS.class))).thenReturn(ResponseEntity.ok(file.toFileDto()));

        ResponseEntity<VFSDto> dEntity = fileCommandController.createNewFile(file);
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
        VFS file = getFile();
        file.setText("some some string");
        when(fileQueryService.updateFile(anyString(), any(VFS.class))).thenReturn(file.toFileDto());

        ResponseEntity<VFSDto> dEntity = fileCommandController.updateFile(String.valueOf(file.getId()), file);
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

    private VFSDto getDirDto(){
        return VFSDto.builder()
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

    private VFSDto getFileDto(){
        return VFSDto.builder()
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

    private VFS getFile(){
        return VFS.builder()
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
