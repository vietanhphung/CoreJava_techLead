package com.java_sql.main.controller.controller_sql;

import com.java_sql.main.entity.User;
import com.java_sql.main.exception.DuplicateUserException;
import com.java_sql.main.security.JwtUtil;
import com.java_sql.main.service.service_sql.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.java_sql.main.dto.AuthRequestDTO;
import com.java_sql.main.dto.RegisterRequestUserDTO;
import com.java_sql.main.dto.RegisterRequestDTO;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    //Register ADMIN auth -> ADMIN + USER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        try{
            User user = userService.registerUser(request.getUsername(), request.getPassword(), request.getEmail(), request.getRole());
            return ResponseEntity.ok(user);
        }
        catch (DuplicateUserException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 409 Conflict
        }
    }

    //register User Only
    @PostMapping("/registerUser")
        public ResponseEntity<?> registerUser(@RequestBody RegisterRequestUserDTO requestUser) {
            try{
                User user = userService.registerUser(requestUser.getUsername(), requestUser.getPassword(), requestUser.getEmail() , "USER");
                return ResponseEntity.ok(user);
            }
            catch (DuplicateUserException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 409 Conflict
            }
        }






      @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Set authentication to SecurityContext (important for session-based security)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get user details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
        } 
        catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid username or password\"}");
        } 
        catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"User account is disabled\"}");
        } 
        catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"User account is locked\"}");
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Authentication failed\"}");
        }
    }
}