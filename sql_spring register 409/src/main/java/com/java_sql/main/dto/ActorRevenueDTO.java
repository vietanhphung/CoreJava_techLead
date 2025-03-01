package com.java_sql.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActorRevenueDTO {
    private String firstName;
    private String lastName;
    private double totalRevenue;
}
