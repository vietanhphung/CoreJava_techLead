package com.java_sql.main.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryRentalDTO {
    private String categoryName;
    private Double avgRentalDuration;
}
