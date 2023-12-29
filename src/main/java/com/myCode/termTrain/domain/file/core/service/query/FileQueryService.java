package com.myCode.termTrain.domain.file.core.service.query;

import com.myCode.termTrain.domain.file.core.FileAction;
import com.myCode.termTrain.domain.file.core.dto.FileDto;
import com.myCode.termTrain.domain.file.core.dto.RequestFile;
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
