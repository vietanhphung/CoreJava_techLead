package com.java_sql.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActorWithFilmCountDTO {
    private String firstName;
    private String lastName;
    private int filmCount;
}
