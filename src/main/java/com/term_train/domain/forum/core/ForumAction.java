package com.term_train.domain.forum.core;

import com.term_train.domain.forum.core.dto.ForumDto;
import com.term_train.domain.forum.core.model.Forum;

import java.util.List;

public interface ForumAction {
    ForumDto createNewForum(Forum forum);

    void deleteForum(Forum forum);

    ForumDto findForumById(Integer id);

    List<ForumDto> findAllForums();
}
