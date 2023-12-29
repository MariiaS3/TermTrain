package com.myCode.termTrain.domain.forum.infrastructure.forum;

import com.myCode.termTrain.domain.forum.core.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Integer> {

}
