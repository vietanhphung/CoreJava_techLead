package com.java_sql.main.service.service_sql;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java_sql.main.entity.Role;
import com.java_sql.main.entity.User;
import com.java_sql.main.repository.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // future implementation
    private final RoleService roleService;




    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createAdminUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        if (password == null ) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        user.setPassword(passwordEncoder.encode(password));        
        user.setRole(roleService.getAdminRole());
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Transactional
    public User createUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        if (password == null ) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleService.getUserRole());
        user.setEmail(email);
        return userRepository.save(user);
    }


    // get role when user log in
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                java.util.List.of(new SimpleGrantedAuthority(user.getRole().getName())) // Change to getRole() look for USER, Not ROLE_USER
        );
    }

    
    
        
    


    
}
