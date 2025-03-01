package com.java_sql.main.controller.controller_sql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java_sql.main.dto.CategoryRentalDTO;
import com.java_sql.main.service.service_sql.CategoryService;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/avg-rental-duration")
    public List<CategoryRentalDTO> getAvgRentalDurationPerCategory() {
        return categoryService.getAvgRentalDurationPerCategory();
    }
}
