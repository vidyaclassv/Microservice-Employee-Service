package com.login.loginService.repository;

import com.login.loginService.model.Users;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(@NonNull String userName);
}
