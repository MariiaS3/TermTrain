package com.myCode.termTrain.domain.forum.core.service.query;

import com.myCode.termTrain.domain.forum.core.ForumAction;
import com.myCode.termTrain.domain.forum.core.dto.ForumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumQueryService {
    private final ForumAction forumAction;

    public List<ForumDto> findAllForums() {
        return forumAction.findAllForums();
    }

    public ForumDto findForumById(Integer id) {
        return forumAction.findForumById(id);
    }
}
