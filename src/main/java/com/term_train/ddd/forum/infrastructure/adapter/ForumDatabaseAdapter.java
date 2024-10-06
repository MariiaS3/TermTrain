package com.term_train.ddd.forum.infrastructure.adapter;

import com.term_train.ddd.forum.domain.model.Forum;
import com.term_train.ddd.forum.infrastructure.port.ForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ForumDatabaseAdapter {
    private final ForumRepository forumRepository;

    public Forum saveAndFlush(Forum forum) {
        return forumRepository.saveAndFlush(forum);
    }

    public void delete(Forum forum) {
        forumRepository.delete(forum);
    }

    public Forum findById(Integer id) {
        return forumRepository.findById(id).get();
    }

    public List<Forum> findAll() {
        return forumRepository.findAll();
    }

}
