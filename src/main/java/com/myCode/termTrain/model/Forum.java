package com.myCode.termTrain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Integer")
    private Integer id;

    private String username;
    private String title;
}
