package com.myCode.termTrain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myCode.termTrain.model.Forum;

public interface ForumRepository extends JpaRepository<Forum, Integer>{
    
}
