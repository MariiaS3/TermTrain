package com.myCode.termTrain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Integer")
    private Integer id;

    private String name;
    private String username;
    private String password;

}
