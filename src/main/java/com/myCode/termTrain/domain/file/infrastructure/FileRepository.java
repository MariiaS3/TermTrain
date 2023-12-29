package com.myCode.termTrain.domain.file.infrastructure;

import com.myCode.termTrain.domain.file.core.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findByName(String name);

    List<File> findByPath(String path);

    File findByPathAndName(String path, String name);
}
