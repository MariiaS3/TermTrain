package com.myCode.termTrain.domain.forum.infrastructure.forum;

import com.myCode.termTrain.domain.forum.core.model.Forum;

import java.util.List;

public interface ForumDatabase {

    Forum saveAndFlush(Forum forum);

    void delete(Forum forum);

    Forum findById(Integer id);

    List<Forum> findAll();
}
