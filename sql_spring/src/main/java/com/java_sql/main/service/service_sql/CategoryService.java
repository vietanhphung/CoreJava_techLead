package com.java_sql.main.service.service_sql;

import org.springframework.stereotype.Service;
import com.java_sql.main.dto.CategoryRentalDTO;
import com.java_sql.main.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryRentalDTO> getAvgRentalDurationPerCategory() {
        List<Object[]> results = categoryRepository.findAvgRentalDurationPerCategory();
        return results.stream()
                .map(row -> new CategoryRentalDTO(
                        (String) row[0],      // category name
                        ((Number) row[1]).doubleValue() // average rental duration
                ))
                .collect(Collectors.toList());
    }
}
