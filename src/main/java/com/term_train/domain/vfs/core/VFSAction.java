package com.term_train.domain.vfs.core;

import com.term_train.domain.vfs.core.dto.VFSDto;
import com.term_train.domain.vfs.core.dto.RequestFile;
import com.term_train.domain.vfs.core.model.VFS;

import java.util.List;

public interface VFSAction {
    VFSDto updateFile(String id, VFS file);

    VFSDto createNewFile(VFS dirFile);

    void delete(VFS file);

    List<VFSDto> findFileByName(String name);

    List<VFSDto> findFileByPath(String path);

    VFSDto findFileByPathAndName(RequestFile fileRequest);
}
