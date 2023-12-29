package com.myCode.termTrain.domain.file.core.model;

import com.myCode.termTrain.domain.file.core.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File {

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

    public File(String name, String path, String permisions, Integer link, String username, String groupname, Integer size, String time, Boolean isDirectory, String text) {
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

    public FileDto toFileDto() {
        return new FileDto(this.getId(), this.getName(), this.getPath(), this.getPermisions(),
                this.getLink(), this.getUsername(), this.getGroupname(), this.getSize(), this.getTime(), this.getIsDirectory(), this.getText());
    }
}
