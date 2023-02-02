package com.myCode.termTrain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myCode.termTrain.model.Dirorfile;

public interface DirorfileRepository extends JpaRepository<Dirorfile, Integer>{
    List<Dirorfile> findByName(String name);
    List<Dirorfile> findByPath(String path);
    Dirorfile findByPathAndName(String path, String name);
}
