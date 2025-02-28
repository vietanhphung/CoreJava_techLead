package com.java_sql.main.controller.controller_sql;


import com.java_sql.main.service.service_sql.JwtTokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;




@RestController
@RequestMapping("/user1")
public class LogoutController_maynotneed {

    @Autowired
    private JwtTokenBlacklistService tokenBlacklistService; // Store invalidated tokens

    @PostMapping("/logout1")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = extractJwtFromRequest(request);
        
        if (token != null) {
            tokenBlacklistService.invalidateToken(token);
        }
        
        return ResponseEntity.ok("Logged out successfully");
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
