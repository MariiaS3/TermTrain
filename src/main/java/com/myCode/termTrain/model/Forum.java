package com.myCode.termTrain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Forum {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Integer")
    private Integer id;

    private String username;
    private String title;

    @OneToMany(mappedBy = "forum")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public void addMessage(ChatMessage chatMessage){
        chatMessages.add(chatMessage);
        chatMessage.setForum(this);
    }

    public void removeMessage(ChatMessage chatMessage){
        chatMessages.remove(chatMessage);
        chatMessage.setForum(null);
    }
}
