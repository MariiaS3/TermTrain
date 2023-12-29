package com.myCode.termTrain.domain.forum.infrastructure.forum;

import com.myCode.termTrain.domain.forum.core.model.Forum;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ForumDatabaseAdapter implements ForumDatabase {
    private final ForumRepository forumRepository;

    @Override
    public Forum saveAndFlush(Forum forum) {
        return forumRepository.saveAndFlush(forum);
    }

    @Override
    public void delete(Forum forum) {
        forumRepository.delete(forum);
    }

    @Override
    public Forum findById(Integer id) {
        return forumRepository.findById(id).get();
    }

    @Override
    public List<Forum> findAll() {
        return forumRepository.findAll();
    }

}
