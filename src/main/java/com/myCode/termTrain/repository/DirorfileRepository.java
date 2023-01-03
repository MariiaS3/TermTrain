package com.myCode.termTrain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myCode.termTrain.model.Dirorfile;

public interface DirorfileRepository extends JpaRepository<Dirorfile, Integer>{
    List<Dirorfile> findDirorfileByName(String name);
    List<Dirorfile> findDirorfileByPath(String path);
    Dirorfile findDirorfileByNameAndPath(String name, String path);
}
