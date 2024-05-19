package com.term_train.domain.forum.core.service.command;

import com.term_train.domain.forum.core.ForumAction;
import com.term_train.domain.forum.core.dto.ForumDto;
import com.term_train.domain.forum.core.model.Forum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumCommandService {
    private final ForumAction forumAction;

    public ForumDto createNewForum(Forum forum) {
        return forumAction.createNewForum(forum);
    }

    public void deleteForum(Forum forum) {
        forumAction.deleteForum(forum);
    }
}
