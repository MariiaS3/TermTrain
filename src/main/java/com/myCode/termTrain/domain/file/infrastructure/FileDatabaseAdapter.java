package com.myCode.termTrain.domain.file.infrastructure;

import com.myCode.termTrain.domain.file.core.model.File;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FileDatabaseAdapter implements FileDatabase {
    private final FileRepository fileRepository;

    @Override
    public File createNewFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File findById(Integer id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public void delete(File file) {
        fileRepository.delete(file);
    }

    @Override
    public List<File> findByName(String name) {
        return fileRepository.findByName(name);
    }

    @Override
    public List<File> findByPath(String path) {
        return fileRepository.findByPath(path);
    }

    @Override
    public File findByPathAndName(String path, String name) {
        return fileRepository.findByPathAndName(path, name);
    }
}
