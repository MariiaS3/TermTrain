package com.myCode.termTrain.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.myCode.termTrain.dto.DirorfileDto;
import com.myCode.termTrain.model.Dirorfile;
import com.myCode.termTrain.repository.DirorfileRepository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DirorfileServiceTest {

    @InjectMocks
    private DirorfileService dirorfileService;

    @Mock
    private DirorfileRepository dirorfileRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void shouldReturnDirsOrFilesByName(){
        List<Dirorfile> dirAndfile = new ArrayList<>();
        Dirorfile dir = getDir();
        dirAndfile.add(dir);
        DirorfileDto dirDto = getDirDto();
        when(dirorfileRepository.findDirorfileByName(anyString())).thenReturn(dirAndfile);
        when(modelMapper.map(dir, DirorfileDto.class)).thenReturn(dirDto);
        List<DirorfileDto> dirDtos = dirorfileService.getDirFileByName("testdir");
        assertThat(dirDtos.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnDirsOrFilesByPath(){
        List<Dirorfile> dirAndfile = new ArrayList<>();
        Dirorfile dir = getDir();
        dirAndfile.add(dir);
        DirorfileDto dirDto = getDirDto();
        when(dirorfileRepository.findDirorfileByPath(anyString())).thenReturn(dirAndfile);
        when(modelMapper.map(dir, DirorfileDto.class)).thenReturn(dirDto);
        List<DirorfileDto> dirDtos = dirorfileService.getDirFileByPath("/testfolder");
        assertThat(dirDtos.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnDirsOrFilesByNameAndPath(){
        Dirorfile dir = getDir();
        DirorfileDto dirDto = getDirDto();
        when(dirorfileRepository.findDirorfileByNameAndPath(anyString(), anyString())).thenReturn(dir);
        when(modelMapper.map(dir, DirorfileDto.class)).thenReturn(dirDto);
        DirorfileDto DtoDir = dirorfileService.getDirFileByNameAndPath("testdir","/testfolder");
        assertThat(DtoDir).isNotNull();
    }


    private Dirorfile getDir(){
        return Dirorfile.builder().id(1).path("/testfolder").name("testdir").link(2).permisions("drwxr-xr-x").username("root").groupname("group").isDirectory(true).size(4096).text("").time("Aug 7 10:51").build();
    }

    private DirorfileDto getDirDto(){
        return DirorfileDto.builder().id(1).path("/testfolder").name("testdir").link(2).permisions("drwxr-xr-x").username("root").groupname("group").isDirectory(true).size(4096).text("").time("Aug 7 10:51").build();
    }
}

