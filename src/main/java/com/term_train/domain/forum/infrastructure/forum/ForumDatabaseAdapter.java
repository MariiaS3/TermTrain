package com.term_train.domain.forum.infrastructure.forum;

import com.term_train.domain.forum.core.model.Forum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
