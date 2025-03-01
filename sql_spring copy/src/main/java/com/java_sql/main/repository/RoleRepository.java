package com.java_sql.main.repository;

import com.java_sql.main.entity.User;
import com.java_sql.main.entity.UserRoleEnum;
import com.java_sql.main.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(UserRoleEnum name);



}
