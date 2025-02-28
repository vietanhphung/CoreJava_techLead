package com.java_sql.main.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java_sql.main.entity.*;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
