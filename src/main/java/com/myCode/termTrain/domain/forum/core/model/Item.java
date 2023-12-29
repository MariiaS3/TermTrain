package com.myCode.termTrain.domain.forum.core.model;

import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Integer")
    private Integer id;

    private String username;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "forum_id", referencedColumnName = "id", nullable = false)
    private Forum forum;

    public Item(String username, String message, Forum forum) {
        this.username = username;
        this.message = message;
        this.forum = forum;
    }

    public ItemDto toItemDto() {
        return new ItemDto(this.getId(), this.getUsername(), this.getMessage(), this.getForum());
    }
}