package com.term_train.domain.vfs.infrastructure;

import com.term_train.domain.vfs.core.model.VFS;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VFSDatabaseAdapter implements VFSDatabase {
    private final VFSRepository fileRepository;

    @Override
    public VFS createNewFile(VFS file) {
        return fileRepository.save(file);
    }

    @Override
    public VFS findById(Integer id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public void delete(VFS file) {
        fileRepository.delete(file);
    }

    @Override
    public List<VFS> findByName(String name) {
        return fileRepository.findByName(name);
    }

    @Override
    public List<VFS> findByPath(String path) {
        return fileRepository.findByPath(path);
    }

    @Override
    public VFS findByPathAndName(String path, String name) {
        return fileRepository.findByPathAndName(path, name);
    }
}
