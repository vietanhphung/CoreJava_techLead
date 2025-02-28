package com.java_sql.main.service.service_sql;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.java_sql.main.entity.*;
import com.java_sql.main.repository.RoleRepository;



@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getAdminRole() {
        return roleRepository.findByName(Role.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role not found"));
    }

    public Role getUserRole() {
        return roleRepository.findByName(Role.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User role not found"));
    }
}

