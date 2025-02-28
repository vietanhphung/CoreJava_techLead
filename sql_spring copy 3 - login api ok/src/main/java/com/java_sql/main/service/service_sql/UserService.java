

package com.java_sql.main.service.service_sql;

import com.java_sql.main.entity.User;
import com.java_sql.main.entity.Role;
import com.java_sql.main.repository.UserRepository;
import com.java_sql.main.repository.RoleRepository;
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
        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            throw new RuntimeException("Username or Email already exists");
        }
        
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
        User user = new User(username, passwordEncoder.encode(password), email);
        user.setRole(role);
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
