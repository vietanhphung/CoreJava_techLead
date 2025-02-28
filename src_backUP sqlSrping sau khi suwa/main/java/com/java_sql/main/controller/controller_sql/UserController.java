package com.java_sql.main.controller.controller_sql;

import com.java_sql.main.entity.User;
import com.java_sql.main.service.service_sql.JwtTokenBlacklistService;
import com.java_sql.main.service.service_sql.JwtService;
import com.java_sql.main.service.service_sql.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenBlacklistService tokenBlacklistService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService, 
                          AuthenticationManager authenticationManager, 
                          JwtTokenBlacklistService tokenBlacklistService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    // Create a new admin user (requires admin authentication)
    @PostMapping("/createAdmin")
    public ResponseEntity<User> createAdminUser(@RequestParam String username, 
                                                @RequestParam String password, 
                                                @RequestParam String email) {
        return ResponseEntity.ok(userService.createAdminUser(username, password, email));
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestParam String username, 
                                           @RequestParam String password, 
                                           @RequestParam String email) {
        return ResponseEntity.ok(userService.createUser(username, password, email));
    }

    // Logout and invalidate token
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

    // Authenticate and generate JWT token
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestParam String username, 
                                          @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(username);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    // Helper method to extract JWT from request
    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }



    // Login method (authenticate and return JWT token)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, 
                                        @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(username);
            return ResponseEntity.ok(token);  // Return JWT token as response
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
