package com.term_train.ddd.vfs.domain.model;

import com.term_train.ddd.vfs.domain.dto.VFSDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VFS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Integer")
    private Integer id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String path;

    @NotNull
    @Column
    private String permisions;

    @NotNull
    @Column
    private Integer link;

    @NotNull
    @Column
    private String username;

    @NotNull
    @Column
    private String groupname;

    @NotNull
    @Column
    private Integer size;

    @NotNull
    @Column
    private String time;

    @NotNull
    @Column
    private Boolean isDirectory;

    @NotNull
    @Column
    private String text;

    public VFS(String name, String path, String permisions, Integer link, String username, String groupname, Integer size, String time, Boolean isDirectory, String text) {
        this.name = name;
        this.path = path;
        this.permisions = permisions;
        this.link = link;
        this.username = username;
        this.groupname = groupname;
        this.size = size;
        this.time = time;
        this.isDirectory = isDirectory;
        this.text = text;
    }

    public VFSDto toFileDto() {
        return new VFSDto(this.getId(), this.getName(), this.getPath(), this.getPermisions(),
                this.getLink(), this.getUsername(), this.getGroupname(), this.getSize(), this.getTime(), this.getIsDirectory(), this.getText());
    }
}
