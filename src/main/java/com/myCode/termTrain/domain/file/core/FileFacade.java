package com.myCode.termTrain.domain.file.core;

import com.myCode.termTrain.domain.file.core.dto.FileDto;
import com.myCode.termTrain.domain.file.core.dto.RequestFile;
import com.myCode.termTrain.domain.file.core.model.File;
import com.myCode.termTrain.domain.file.infrastructure.FileDatabase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FileFacade implements FileAction {

    private final FileDatabase fileDatabase;

    private FileDto convertFileToDto(File dirorfile) {
        return dirorfile.toFileDto();
    }

    public FileDto updateFile(String id, File file) {
        try {
            File fileTemp = fileDatabase.findById(Integer.valueOf(id));
            fileTemp.setName(file.getName());
            fileTemp.setPath(file.getPath());
            fileTemp.setText(file.getText());
            fileTemp.setPermisions(file.getPermisions());
            fileTemp.setSize(file.getSize());
            fileTemp.setLink(file.getLink());
            fileTemp.setTime(file.getTime());
            fileTemp.setUsername(file.getUsername());
            fileTemp.setGroupname(file.getGroupname());
            return fileDatabase.createNewFile(fileTemp).toFileDto();
        } catch (Exception ex) {
            throw new RuntimeException("File with id: " + id + " don't exist");
        }
    }

    @Override
    public FileDto createNewFile(File file) {
        return fileDatabase.createNewFile(file).toFileDto();
    }

    @Override
    public void delete(File file) {
        fileDatabase.delete(file);
    }

    @Override
    public List<FileDto> findFileByName(String name) {
        List<File> dirFiles = fileDatabase.findByName(name);
        return dirFiles.stream().map(this::convertFileToDto).collect(Collectors.toList());
    }

    @Override
    public List<FileDto> findFileByPath(String path) {
        path = path.replace('-', '/');
        List<File> dirFiles = fileDatabase.findByPath(path);
        return dirFiles.stream().map(this::convertFileToDto).collect(Collectors.toList());
    }

    @Override
    public FileDto findFileByPathAndName(RequestFile fileRequest) {
        String path = fileRequest.getPath().replace('-', '/');
        String name = fileRequest.getName();
        File dirFile = fileDatabase.findByPathAndName(path, name);
        return dirFile.toFileDto();
    }
}
