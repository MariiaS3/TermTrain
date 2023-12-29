package com.myCode.termTrain.domain.forum.core;

import com.myCode.termTrain.domain.forum.core.dto.ForumDto;
import com.myCode.termTrain.domain.forum.core.model.Forum;

import java.util.List;

public interface ForumAction {
    ForumDto createNewForum(Forum forum);

    void deleteForum(Forum forum);

    ForumDto findForumById(Integer id);

    List<ForumDto> findAllForums();
}
