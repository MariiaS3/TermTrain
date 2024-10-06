package com.term_train.ddd.forum.infrastructure.port;

import com.term_train.ddd.forum.domain.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Integer> {

}
