package com.term_train.ddd.vfs.infrastructure.port;

import com.term_train.ddd.vfs.domain.model.VFS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VFSRepository extends JpaRepository<VFS, Integer> {
    List<VFS> findByName(String name);

    List<VFS> findByPath(String path);

    VFS findByPathAndName(String path, String name);

}
