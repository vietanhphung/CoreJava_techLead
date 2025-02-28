package com.java_sql.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.java_sql.main.security.JwtAuthenticationFilter;
import com.java_sql.main.service.service_sql.JwtTokenBlacklistService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.util.StringUtils;
import com.java_sql.main.controller.controller_sql.UserController;
import com.java_sql.main.service.service_sql.JwtTokenBlacklistService;

@Configuration
public class SecurityConfig {





    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/register").permitAll()
                .requestMatchers("/auth/login").permitAll()


                .requestMatchers("/user/logout").permitAll()
                .requestMatchers("/spring1/**","/spring2/**", "/spring3/**", "/spring4/**").hasAuthority("USER")
                .requestMatchers("/actor/**", "/categories", "/updateQuery", "/user/createAdmin").hasAuthority("ADMIN")

                .anyRequest().authenticated()
            )
            .logout(logout -> logout
                .logoutUrl("/user/logout")  // Define logout endpoint
                .logoutSuccessHandler((request, response, authentication) -> {
                    String token = extractJwtFromRequest(request);

                    if (token != null) {
                        JwtTokenBlacklistService jwtTokenBlacklistService = new JwtTokenBlacklistService();
                        jwtTokenBlacklistService.invalidateToken(token); // Blacklist token
                    }

                    SecurityContextHolder.clearContext(); // Clears authentication

                    response.setStatus(200);
                    response.getWriter().write("Logout successful");
                    response.getWriter().flush();
                })

                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

        private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    
}
