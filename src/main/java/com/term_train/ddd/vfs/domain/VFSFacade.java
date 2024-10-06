package com.term_train.ddd.vfs.domain;

import com.term_train.ddd.vfs.infrastructure.adapter.VFSDatabaseAdapter;
import com.term_train.ddd.vfs.domain.dto.VFSDto;
import com.term_train.ddd.vfs.domain.dto.RequestFile;
import com.term_train.ddd.vfs.domain.model.VFS;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VFSFacade {

    private final VFSDatabaseAdapter fileDatabase;

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

    public VFSDto createNewFile(VFS file) {
        return fileDatabase.createNewFile(file).toFileDto();
    }

    public void delete(VFS file) {
        fileDatabase.delete(file);
    }

    public List<VFSDto> findFileByName(String name) {
        List<VFS> dirFiles = fileDatabase.findByName(name);
        return dirFiles.stream().map(this::convertFileToDto).collect(Collectors.toList());
    }

    public List<VFSDto> findFileByPath(String path) {
        path = path.replace('-', '/');
        List<VFS> dirFiles = fileDatabase.findByPath(path);
        return dirFiles.stream().map(this::convertFileToDto).collect(Collectors.toList());
    }

    public VFSDto findFileByPathAndName(RequestFile fileRequest) {
        String path = fileRequest.getFile().replace('-', '/');
        String name = fileRequest.getCommandName();
        VFS dirFile = fileDatabase.findByPathAndName(path, name);
        return dirFile.toFileDto();
    }

    public String getFileTextByPathAndName(RequestFile fileRequest) {
        String path = fileRequest.getFile().replace('-', '/');
        String name = fileRequest.getCommandName();
        VFS dirFile = fileDatabase.findByPathAndName(path, name);
        return dirFile.getText();
    }
}
