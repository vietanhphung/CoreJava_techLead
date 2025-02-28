package com.java_sql.main.service.service_sql;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;



@Service
public class JwtTokenBlacklistService {

    private final Set<String> blacklistedTokens = new HashSet<>();

    public  void invalidateToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
