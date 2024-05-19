package com.term_train.domain.file.core;

import com.term_train.domain.file.core.dto.FileDto;
import com.term_train.domain.file.core.dto.RequestFile;
import com.term_train.domain.file.core.model.File;

import java.util.List;

public interface FileAction {
    FileDto updateFile(String id, File file);

    FileDto createNewFile(File dirFile);

    void delete(File file);

    List<FileDto> findFileByName(String name);

    List<FileDto> findFileByPath(String path);

    FileDto findFileByPathAndName(RequestFile fileRequest);
}
