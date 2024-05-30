package com.term_train.domain.vfs.core.service.command;

import com.term_train.domain.vfs.core.VFSAction;
import com.term_train.domain.vfs.core.dto.VFSDto;
import com.term_train.domain.vfs.core.model.VFS;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileCommandService {
    private final VFSAction fileAction;

    public VFSDto createNewFile(VFS dirFile) {
        return fileAction.createNewFile(dirFile);
    }

    public VFSDto updateFile(String id, VFS file) {
        return fileAction.updateFile(id, file);

    }

    // public void delete(Dirorfile dirorfile){
    //     dirFileRepository.delete(dirorfile);
    // }
}
