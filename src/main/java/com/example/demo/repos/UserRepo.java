package com.example.demo.repos;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllById(Long id);

    User findByActivationCode(String code);
}
