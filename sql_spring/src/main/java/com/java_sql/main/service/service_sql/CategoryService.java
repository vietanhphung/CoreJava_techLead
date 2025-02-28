package com.java_sql.main.service.service_sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_sql.main.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Object> getAvgRentalDurationPerCategory() {
        return categoryRepository.findAvgRentalDurationPerCategory();
    }
}
