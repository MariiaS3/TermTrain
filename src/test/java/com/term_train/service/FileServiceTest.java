package com.term_train.service;

import com.term_train.ddd.vfs.infrastructure.service.FileCommandService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.term_train.ddd.vfs.domain.dto.VFSDto;
import com.term_train.ddd.vfs.domain.model.VFS;
import com.term_train.ddd.vfs.infrastructure.port.VFSRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @InjectMocks
    private FileCommandService fileCommandService;
    @InjectMocks
    private FileCommandService fileQueryService;
    @Mock
    private VFSRepository dirorfileRepository;

    @Mock
    private ModelMapper modelMapper;

//    @Test
//    void shouldReturnDirsOrFilesByName(){
//        List<File> dirAndfile = new ArrayList<>();
//        File dir = getDir();
//        dirAndfile.add(dir);
//        FileDto dirDto = getDirDto();
//        when(dirorfileRepository.findByName(anyString())).thenReturn(dirAndfile);
//        when(modelMapper.map(dir, FileDto.class)).thenReturn(dirDto);
//        List<FileDto> dirDtos = fileQueryService.getFileByName("testdir");
//        assertThat(dirDtos.size()).isEqualTo(1);
//    }
//
//    @Test
//    void shouldReturnDirsOrFilesByPath(){
//        List<File> dirAndfile = new ArrayList<>();
//        File dir = getDir();
//        dirAndfile.add(dir);
//        FileDto dirDto = getDirDto();
//        when(dirorfileRepository.findByPath(anyString())).thenReturn(dirAndfile);
//        when(modelMapper.map(dir, FileDto.class)).thenReturn(dirDto);
//        List<FileDto> dirDtos = fileQueryService.getFileByPath("/testfolder");
//        assertThat(dirDtos.size()).isEqualTo(1);
//    }
//
//    @Test
//    void shouldReturnDirsOrFilesByNameAndPath(){
//        File dir = getDir();
//        FileDto dirDto = getDirDto();
//        when(dirorfileRepository.findByPathAndName(anyString(), anyString())).thenReturn(dir);
//        when(modelMapper.map(dir, FileDto.class)).thenReturn(dirDto);
//
//        RequestFile requestFile = new RequestFile("testdir", "/testfolder");
//
//        FileDto DtoDir = fileQueryService.getFileByPathAndName(requestFile);
//        assertThat(DtoDir).isNotNull();
//    }


    private VFS getDir(){
        return VFS.builder()
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
}

