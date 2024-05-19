package com.term_train.domain.file.infrastructure;

import com.term_train.domain.file.core.model.File;

import java.util.List;

public interface FileDatabase {
    File createNewFile(File dirFile);

    File findById(Integer id);

    void delete(File file);

    List<File> findByName(String name);

    List<File> findByPath(String path);

    File findByPathAndName(String path, String name);

}
