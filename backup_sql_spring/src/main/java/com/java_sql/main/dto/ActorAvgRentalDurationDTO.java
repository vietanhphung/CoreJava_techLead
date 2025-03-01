package com.java_sql.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActorAvgRentalDurationDTO {
    private String firstName;
    private String lastName;
    private String category;
    private double avgRentalDuration;
}
