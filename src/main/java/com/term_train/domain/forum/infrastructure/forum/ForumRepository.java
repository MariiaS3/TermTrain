package com.term_train.domain.forum.infrastructure.forum;

import com.term_train.domain.forum.core.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Integer> {

}
