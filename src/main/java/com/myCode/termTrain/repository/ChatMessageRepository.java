package com.myCode.termTrain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myCode.termTrain.model.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findByForumId(Integer id);
}
