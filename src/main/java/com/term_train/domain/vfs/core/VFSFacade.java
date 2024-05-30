package com.term_train.domain.vfs.core;

import com.term_train.domain.vfs.core.dto.VFSDto;
import com.term_train.domain.vfs.core.dto.RequestFile;
import com.term_train.domain.vfs.core.model.VFS;
import com.term_train.domain.vfs.infrastructure.VFSDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VFSFacade implements VFSAction {

    private final VFSDatabase fileDatabase;

    private VFSDto convertFileToDto(VFS dirorfile) {
        return dirorfile.toFileDto();
    }

    public VFSDto updateFile(String id, VFS file) {
        try {
            VFS fileTemp = fileDatabase.findById(Integer.valueOf(id));
            fileTemp.setName(file.getName());
            fileTemp.setPath(file.getPath());
            fileTemp.setText(file.getText());
            fileTemp.setPermisions(file.getPermisions());
            fileTemp.setSize(file.getSize());
            fileTemp.setLink(file.getLink());
            fileTemp.setTime(file.getTime());
            fileTemp.setUsername(file.getUsername());
            fileTemp.setGroupname(file.getGroupname());
            return fileDatabase.createNewFile(fileTemp).toFileDto();
        } catch (Exception ex) {
            throw new RuntimeException("File with id: " + id + " don't exist");
        }
    }

    @Override
    public VFSDto createNewFile(VFS file) {
        return fileDatabase.createNewFile(file).toFileDto();
    }

    @Override
    public void delete(VFS file) {
        fileDatabase.delete(file);
    }

    @Override
    public List<VFSDto> findFileByName(String name) {
        List<VFS> dirFiles = fileDatabase.findByName(name);
        return dirFiles.stream().map(this::convertFileToDto).collect(Collectors.toList());
    }

    @Override
    public List<VFSDto> findFileByPath(String path) {
        path = path.replace('-', '/');
        List<VFS> dirFiles = fileDatabase.findByPath(path);
        return dirFiles.stream().map(this::convertFileToDto).collect(Collectors.toList());
    }

    @Override
    public VFSDto findFileByPathAndName(RequestFile fileRequest) {
        String path = fileRequest.getPath().replace('-', '/');
        String name = fileRequest.getName();
        VFS dirFile = fileDatabase.findByPathAndName(path, name);
        return dirFile.toFileDto();
    }
}
