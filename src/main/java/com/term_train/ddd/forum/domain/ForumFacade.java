package com.term_train.ddd.forum.domain;

import com.term_train.ddd.forum.domain.dto.ForumDto;
import com.term_train.ddd.forum.domain.model.Forum;
import com.term_train.ddd.forum.infrastructure.adapter.ForumDatabaseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumFacade {

    private final ForumDatabaseAdapter forumDatabase;

    private ForumDto convertForumToDto(Forum forum) {
        return forum.toForumDto();
    }

    public ForumDto createNewForum(Forum forum) {
        return forumDatabase.saveAndFlush(forum).toForumDto();
    }

    public void deleteForum(Forum forum) {
        forumDatabase.delete(forum);
    }

    public ForumDto findForumById(Integer id) {
        Forum forum = forumDatabase.findById(id);
        return forum.toForumDto();
    }

    public List<ForumDto> findAllForums() {
        List<Forum> forum = forumDatabase.findAll();
        return forum.stream().map(this::convertForumToDto).collect(Collectors.toList());
    }
}
