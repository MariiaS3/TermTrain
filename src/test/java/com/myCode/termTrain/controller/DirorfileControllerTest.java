package com.myCode.termTrain.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.myCode.termTrain.dto.DirorfileDto;
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
        when(dirorfileService.getDirFileByName(anyString())).thenReturn(dirorfileDtos);
        ResponseEntity<List<DirorfileDto>> dEntity = dirorfileController.getDirFileByName("testdir");
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    void shouldRetornDirOrFileWhenGetDirOrFileByPathColled(){
        List<DirorfileDto> dirorfileDtos = new ArrayList<>();
        dirorfileDtos.add(getDirDto());
        when(dirorfileService.getDirFileByPath(anyString())).thenReturn(dirorfileDtos);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/v1/path//testfolder");
        ResponseEntity<List<DirorfileDto>> dEntity = dirorfileController.getDirFileByPath(request);
        assertThat(dEntity).isNotNull();
        assertThat(dEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    void shouldRetornDirOrFileWhenGetDirOrFileByNameAndPathColled(){
        DirorfileDto dirorfileDto = getDirDto();
        when(dirorfileService.getDirFileByNameAndPath(anyString(), anyString())).thenReturn(dirorfileDto);
        
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/v1/path-and-name//testfolder/testdir");
        ResponseEntity<DirorfileDto> dEntity = dirorfileController.getDirFileByNameAndPath(request);
        assertThat(dEntity).isNotNull();
    }

    private DirorfileDto getDirDto(){
        return DirorfileDto.builder().id(1).path("/testfolder").name("testdir").link(2).permisions("drwxr-xr-x").username("root").groupname("group").isDirectory(true).size(4096).text("").time("Aug 7 10:51").build();
    }
}
