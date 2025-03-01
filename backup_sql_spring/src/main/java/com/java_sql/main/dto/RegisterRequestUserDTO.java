package com.java_sql.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RegisterRequestUserDTO {
    private String username;
    private String password;
    private String email;}

   