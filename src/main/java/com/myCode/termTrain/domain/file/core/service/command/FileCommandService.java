package com.myCode.termTrain.domain.file.core.service.command;

import com.myCode.termTrain.domain.file.core.FileAction;
import com.myCode.termTrain.domain.file.core.dto.FileDto;
import com.myCode.termTrain.domain.file.core.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileCommandService {
    private final FileAction fileAction;

    public FileDto createNewFile(File dirFile) {
        return fileAction.createNewFile(dirFile);
    }

    public FileDto updateFile(String id, File file) {
        return fileAction.updateFile(id, file);

    }

    // public void delete(Dirorfile dirorfile){
    //     dirFileRepository.delete(dirorfile);
    // }
}
