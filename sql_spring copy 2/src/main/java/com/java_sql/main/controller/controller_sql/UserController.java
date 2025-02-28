package com.java_sql.main.controller.controller_sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.java_sql.main.entity.*;
import com.java_sql.main.service.service_sql.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // Create a new admin user // needs admin authen to create
    @PostMapping("/createAdmin")
    public ResponseEntity<User> createAdminUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return ResponseEntity.ok(userService.createAdminUser(username, password, email));
    }

    // Create a new user with a specific role
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestParam String username, 
                                           @RequestParam String password, 
                                           @RequestParam String email) {
        return ResponseEntity.ok(userService.createUser(username, password,email));
    }






    @Autowired
    private JwtTokenBlacklistService tokenBlacklistService; // Store invalidated tokens

    @PostMapping("/logout")
public ResponseEntity<?> logout(HttpServletRequest request) {
    String token = extractJwtFromRequest(request);

    if (token == null) {
        return ResponseEntity.status(401).body("No token provided");
    }

    tokenBlacklistService.invalidateToken(token);
    SecurityContextHolder.clearContext(); // Ensure context is cleared

    return ResponseEntity.ok("Logged out successfully");
}


    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
