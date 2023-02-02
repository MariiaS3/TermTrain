package com.myCode.termTrain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        when(dirorfileRepository.findByName(anyString())).thenReturn(dirAndfile);
        when(modelMapper.map(dir, DirorfileDto.class)).thenReturn(dirDto);
        List<DirorfileDto> dirDtos = dirorfileService.getByName("testdir");
        assertThat(dirDtos.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnDirsOrFilesByPath(){
        List<Dirorfile> dirAndfile = new ArrayList<>();
        Dirorfile dir = getDir();
        dirAndfile.add(dir);
        DirorfileDto dirDto = getDirDto();
        when(dirorfileRepository.findByPath(anyString())).thenReturn(dirAndfile);
        when(modelMapper.map(dir, DirorfileDto.class)).thenReturn(dirDto);
        List<DirorfileDto> dirDtos = dirorfileService.getByPath("/testfolder");
        assertThat(dirDtos.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnDirsOrFilesByNameAndPath(){
        Dirorfile dir = getDir();
        DirorfileDto dirDto = getDirDto();
        when(dirorfileRepository.findByPathAndName(anyString(), anyString())).thenReturn(dir);
        when(modelMapper.map(dir, DirorfileDto.class)).thenReturn(dirDto);
        DirorfileDto DtoDir = dirorfileService.getByPathAndName("/testfolder", "testdir");
        assertThat(DtoDir).isNotNull();
    }


    private Dirorfile getDir(){
        return Dirorfile.builder().id(UUID.fromString("b5607d38-8fc1-43ef-b44e-34967083c80a")).path("/testfolder").name("testdir").link(2).permisions("drwxr-xr-x").username("root").groupname("group").isDirectory(true).size(4096).text("").time("Aug 7 10:51").build();
    }

    private DirorfileDto getDirDto(){
        return DirorfileDto.builder().id(UUID.fromString("a335b2d8-f674-4d98-9867-b60a66efef62")).path("/testfolder").name("testdir").link(2).permisions("drwxr-xr-x").username("root").groupname("group").isDirectory(true).size(4096).text("").time("Aug 7 10:51").build();
    }
}

