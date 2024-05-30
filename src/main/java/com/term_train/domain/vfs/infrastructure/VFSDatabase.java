package com.term_train.domain.vfs.infrastructure;

import com.term_train.domain.vfs.core.model.VFS;

import java.util.List;

public interface VFSDatabase {
    VFS createNewFile(VFS dirFile);

    VFS findById(Integer id);

    void delete(VFS file);

    List<VFS> findByName(String name);

    List<VFS> findByPath(String path);

    VFS findByPathAndName(String path, String name);

}
