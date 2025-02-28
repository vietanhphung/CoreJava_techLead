package com.java_sql.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java_sql.main.entity.User;

import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
