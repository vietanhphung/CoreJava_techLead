package com.java_sql.main.service.service_sql;

import com.java_sql.main.entity.User;
import com.java_sql.main.entity.UserRoleEnum;
import com.java_sql.main.entity.Role;
import com.java_sql.main.repository.UserRepository;
import com.java_sql.main.repository.RoleRepository;
import com.java_sql.main.exception.DuplicateUserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }




    public User registerUser(String username, String password, String email, String roleName) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUserException("Username is already taken.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException("Email is already registered.");
        }
    
        // Convert roleName (String) to Enum
        UserRoleEnum roleEnum;
        try {
            roleEnum = UserRoleEnum.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + roleName);
        }
    
        // Find role in DB
        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleEnum));
    
        User user = new User(username, passwordEncoder.encode(password), email, role);
        return userRepository.save(user);
    }
    






    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
