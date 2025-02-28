package com.sql.main.service;

import com.sql.main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
