package com.myCode.termTrain.domain.forum.core.model;

import com.myCode.termTrain.domain.forum.core.dto.ForumDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Integer")
    private Integer id;

    private String username;
    private String title;

    public Forum(String username, String title) {
        this.username = username;
        this.title = title;
    }

    public ForumDto toForumDto() {
        return new ForumDto(this.getId(), this.getUsername(), this.getTitle());
    }
}
