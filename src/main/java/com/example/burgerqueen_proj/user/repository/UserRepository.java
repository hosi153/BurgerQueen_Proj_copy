package com.example.burgerqueen_proj.user.repository;

import com.example.burgerqueen_proj.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
