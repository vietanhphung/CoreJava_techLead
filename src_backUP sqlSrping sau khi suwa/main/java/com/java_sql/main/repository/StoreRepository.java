package com.java_sql.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java_sql.main.entity.*;

import java.util.List;

@Repository
public interface StoreRepository  extends JpaRepository<Store, Long>{
    @Query(value = """
        SELECT s.store_id, SUM(p.amount) AS total_revenue
        FROM payment p
        JOIN staff st ON p.staff_id = st.staff_id
        JOIN store s ON st.store_id = s.store_id
        WHERE YEAR(p.payment_date) = 2021
        GROUP BY s.store_id;
    """, nativeQuery = true)
    List<Object> findRevenueByStoreFor2021();



}