package com.java_sql.main.controller.controller_sql;

import com.java_sql.main.dto.LoginRequest;
import com.java_sql.main.dto.AuthResponse;
import com.java_sql.main.service.service_sql.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
