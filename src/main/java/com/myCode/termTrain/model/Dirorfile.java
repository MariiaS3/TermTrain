package com.myCode.termTrain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dirorfile {
    
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


}
