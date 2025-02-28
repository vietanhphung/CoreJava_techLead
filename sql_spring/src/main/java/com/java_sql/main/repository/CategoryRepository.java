package com.java_sql.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java_sql.main.entity.*;

import java.util.List;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>{

    @Query(value = """
        SELECT c.name AS category, 
               AVG(DATEDIFF(r.return_date, r.rental_date)) AS avg_rental_duration_of_category
        FROM rental r
        JOIN inventory i ON r.inventory_id = i.inventory_id
        JOIN film f ON i.film_id = f.film_id
        JOIN film_category fc ON f.film_id = fc.film_id
        JOIN category c ON fc.category_id = c.category_id
        WHERE r.return_date IS NOT NULL
        GROUP BY c.category_id;
    """, nativeQuery = true)
    List<Object> findAvgRentalDurationPerCategory();

    
}

    
