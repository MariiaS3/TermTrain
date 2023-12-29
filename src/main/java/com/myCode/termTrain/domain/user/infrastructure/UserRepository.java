package com.myCode.termTrain.domain.user.infrastructure;

import com.myCode.termTrain.domain.user.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String email);
}
