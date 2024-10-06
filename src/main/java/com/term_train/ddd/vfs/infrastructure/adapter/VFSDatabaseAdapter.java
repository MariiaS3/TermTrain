package com.term_train.ddd.vfs.infrastructure.adapter;

import com.term_train.ddd.vfs.domain.model.VFS;
import com.term_train.ddd.vfs.infrastructure.port.VFSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VFSDatabaseAdapter {
    private final VFSRepository fileRepository;

    public VFS createNewFile(VFS file) {
        return fileRepository.save(file);
    }

    public VFS findById(Integer id) {
        return fileRepository.findById(id).get();
    }

    public void delete(VFS file) {
        fileRepository.delete(file);
    }

    public List<VFS> findByName(String name) {
        return fileRepository.findByName(name);
    }

    public List<VFS> findByPath(String path) {
        return fileRepository.findByPath(path);
    }

    public VFS findByPathAndName(String path, String name) {
        return fileRepository.findByPathAndName(path, name);
    }
}
