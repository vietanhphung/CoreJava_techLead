package com.java_sql.main.entity;
public class AuthRequest {

    private String username;
    private String password;

    // Default constructor (NoArgsConstructor)
    public AuthRequest() {}

    // All-args constructor
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Optionally, you could add toString(), equals(), hashCode() methods manually as well.
}
