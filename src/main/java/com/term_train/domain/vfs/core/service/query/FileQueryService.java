package com.term_train.domain.vfs.core.service.query;

import com.term_train.domain.vfs.core.VFSAction;
import com.term_train.domain.vfs.core.dto.VFSDto;
import com.term_train.domain.vfs.core.dto.RequestFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileQueryService {
    private final VFSAction fileAction;

    public List<VFSDto> getFileByName(String name) {
        return fileAction.findFileByName(name);
    }

    public List<VFSDto> getFileByPath(String path) {
        return fileAction.findFileByPath(path);
    }

    public VFSDto getFileByPathAndName(RequestFile fileRequest) {
        return fileAction.findFileByPathAndName(fileRequest);
    }
}
