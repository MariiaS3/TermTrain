package com.myCode.termTrain.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.myCode.termTrain.dto.DirorfileDto;
import com.myCode.termTrain.dto.FileRequest;
import com.myCode.termTrain.service.DirorfileService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DirorfileControllerTest {
    
    @InjectMocks
    private DirorfileController dirorfileController;

    @Mock
    private DirorfileService dirorfileService;

    @Test
    void shouldRetornDirOrFileWhenGetDirOrFileByNameColled(){
        List<DirorfileDto> dirorfileDtos = new ArrayList<>();
        dirorfileDtos.add(getDirDto());
        when(dirorfileService.getByName(anyString())).thenReturn(dirorfileDtos);
        ResponseEntity<List<DirorfileDto>> dEntity = dirorfileController.getDirFileByName("testdir");
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    void shouldRetornDirOrFileWhenGetDirOrFileByPathColled(){
        List<DirorfileDto> dirorfileDtos = new ArrayList<>();
        dirorfileDtos.add(getDirDto());
        when(dirorfileService.getByPath(anyString())).thenReturn(dirorfileDtos);
        ResponseEntity<List<DirorfileDto>> dEntity = dirorfileController.getDirFileByPath("-testfolder");
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    void shouldRetornDirOrFileWhenGetDirOrFileByNameAndPathColled(){
        DirorfileDto dirorfileDto = getFileDto();
        when(dirorfileService.getByPathAndName(anyString(), anyString())).thenReturn(dirorfileDto);
        
        FileRequest file =  new FileRequest("test.txt","/api/v1/path-name/-testdir-");
        ResponseEntity<DirorfileDto> dEntity = dirorfileController.getDirFileByNameAndPath(file);
        assertThat(dEntity).isNotNull();
    }

    private DirorfileDto getDirDto(){
        return DirorfileDto.builder().id(1).path("/testfolder").name("testdir").link(2).permisions("drwxr-xr-x").username("root").groupname("group").isDirectory(true).size(4096).text("").time("Aug 7 10:51").build();
    }

    private DirorfileDto getFileDto(){
        return DirorfileDto.builder().id(1).path("test.txt").name("/testdir").link(1).permisions("-rw-r--r--").username("root").groupname("group").isDirectory(false).size(4096).text("some string").time("Aug 7 10:51").build();
    }
}
