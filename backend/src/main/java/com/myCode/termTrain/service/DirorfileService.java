package com.myCode.termTrain.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.myCode.termTrain.dto.DirorfileDto;
import com.myCode.termTrain.model.Dirorfile;
import com.myCode.termTrain.repository.DirorfileRepository;

@Service
public class DirorfileService {
    
    private final DirorfileRepository dirFileRepository;
    private final ModelMapper modelMapper;

    public DirorfileService(DirorfileRepository dirFileRepository, ModelMapper modelMapper) {
        this.dirFileRepository = dirFileRepository;
        this.modelMapper = modelMapper;
    }

    private Function<? super Dirorfile, ? extends DirorfileDto> convertDirFileToDto(){
        return dirFile -> modelMapper.map(dirFile, DirorfileDto.class);
    }

    public Dirorfile addNewDirOrFile(Dirorfile dirFile){
        return dirFileRepository.save(dirFile);
    }

    public List<DirorfileDto> getDirFileByName(String name){
        List<Dirorfile> dirFiles = dirFileRepository.findDirorfileByName(name);
        return dirFiles.stream().map(convertDirFileToDto()).collect(Collectors.toList());
    }
    
    public List<DirorfileDto> getDirFileByPath(String path){
        List<Dirorfile> dirFiles = dirFileRepository.findDirorfileByPath(path);
        return dirFiles.stream().map(convertDirFileToDto()).collect(Collectors.toList());
    }

    public DirorfileDto getDirFileByNameAndPath(String name, String path){
        Dirorfile dirFile = dirFileRepository.findDirorfileByNameAndPath(name, path);
        DirorfileDto dirFileDto = modelMapper.map(dirFile, DirorfileDto.class);
        return dirFileDto;
    }
}
