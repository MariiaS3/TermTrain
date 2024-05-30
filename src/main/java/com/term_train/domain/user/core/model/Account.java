package com.term_train.domain.user.core.model;

import com.term_train.domain.user.core.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;

    public Account(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public AccountDto toUserDTO() {
        return new AccountDto(this.getId(), this.getName(), this.getUsername(), this.getPassword(), this.getEmail());
    }
}
