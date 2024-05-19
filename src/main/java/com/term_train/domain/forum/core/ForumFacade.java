package com.term_train.domain.forum.core;

import com.term_train.domain.forum.core.dto.ForumDto;
import com.term_train.domain.forum.core.model.Forum;
import com.term_train.domain.forum.infrastructure.forum.ForumDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumFacade implements ForumAction {

    private final ForumDatabase forumDatabase;

    private ForumDto convertForumToDto(Forum forum) {
        return forum.toForumDto();
    }

    @Override
    public ForumDto createNewForum(Forum forum) {
        return forumDatabase.saveAndFlush(forum).toForumDto();
    }

    @Override
    public void deleteForum(Forum forum) {
        forumDatabase.delete(forum);
    }

    @Override
    public ForumDto findForumById(Integer id) {
        Forum forum = forumDatabase.findById(id);
        return forum.toForumDto();
    }

    @Override
    public List<ForumDto> findAllForums() {
        List<Forum> forum = forumDatabase.findAll();
        return forum.stream().map(this::convertForumToDto).collect(Collectors.toList());
    }
}
