package com.term_train.domain.file.core.service.query;

import com.term_train.domain.file.core.FileAction;
import com.term_train.domain.file.core.dto.FileDto;
import com.term_train.domain.file.core.dto.RequestFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileQueryService {
    private final FileAction fileAction;

    public List<FileDto> getFileByName(String name) {
        return fileAction.findFileByName(name);
    }

    public List<FileDto> getFileByPath(String path) {
        return fileAction.findFileByPath(path);
    }

    public FileDto getFileByPathAndName(RequestFile fileRequest) {
        return fileAction.findFileByPathAndName(fileRequest);
    }
}
