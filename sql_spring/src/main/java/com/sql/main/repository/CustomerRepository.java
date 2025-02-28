package com.sql.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.sql.main.entity.*;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long>{

    @Query(value = """
        SELECT DISTINCT c.first_name, c.last_name, a.address 
        FROM customer c
        JOIN rental r ON c.customer_id = r.customer_id
        JOIN address a ON c.address_id = a.address_id
        WHERE r.rental_date BETWEEN '2022-01-01' AND '2022-01-31';
    """, nativeQuery = true)
    List<Object> findCustomersWhoRentedInJan2022();


    @Query(value = """
        SELECT c.first_name, c.last_name, SUM(p.amount) AS total_revenue
        FROM customer c
        JOIN payment p ON c.customer_id = p.customer_id
        GROUP BY c.customer_id
        ORDER BY total_revenue DESC
        LIMIT 10;
    """, nativeQuery = true)
    List<Object> findTop10CustomersByRevenue();

    @Query(value = """
        SELECT c.first_name, c.last_name, c.email, a.address
        FROM customer c
        JOIN rental r ON c.customer_id = r.customer_id
        JOIN inventory i ON r.inventory_id = i.inventory_id
        JOIN film_category fc ON i.film_id = fc.film_id
        JOIN category cat ON fc.category_id = cat.category_id
        JOIN address a ON c.address_id = a.address_id
        GROUP BY c.customer_id
        HAVING COUNT(DISTINCT cat.category_id) = (SELECT COUNT(*) FROM category);
    """, nativeQuery = true)
    List<Object> findCustomersWhoRentedAllCategories();


    @Query(value = """
        SELECT c.first_name, c.last_name, f.title, COUNT(*) AS rental_count
        FROM rental r
        JOIN inventory i ON r.inventory_id = i.inventory_id
        JOIN film f ON i.film_id = f.film_id
        JOIN customer c ON r.customer_id = c.customer_id
        GROUP BY c.customer_id, f.film_id
        HAVING COUNT(*) > 1;
    """, nativeQuery = true)
    List<Object> findCustomersWhoRentedSameFilmMultipleTimes();

    @Query(value = """
        SELECT DISTINCT c.first_name, c.last_name
        FROM customer c
        JOIN rental r ON c.customer_id = r.customer_id
        JOIN inventory i ON r.inventory_id = i.inventory_id
        JOIN film_category fc ON i.film_id = fc.film_id
        WHERE NOT EXISTS (
            SELECT 1
            FROM rental r2
            JOIN inventory i2 ON r2.inventory_id = i2.inventory_id
            JOIN film_category fc2 ON i2.film_id = fc2.film_id
            WHERE r2.customer_id = c.customer_id
            AND fc2.category_id = fc.category_id
            AND r2.rental_date < r.rental_date
        );
    """, nativeQuery = true)
    List<Object> findCustomersWhoRentedNewCategory();


    @Query(value = """
    SELECT c.first_name, 
           c.last_name, 
           i.film_id, 
           COUNT(r.rental_id) AS total_films, 
           SUM(p.amount) AS total_fee
    FROM customer c
    JOIN rental r ON c.customer_id = r.customer_id
    JOIN payment p ON r.rental_id = p.rental_id
    JOIN inventory i ON r.inventory_id = i.inventory_id
    GROUP BY c.customer_id, 
             i.film_id
    HAVING COUNT(r.rental_id) > 10
    """, nativeQuery = true)
List<Object> getCustomersWithMoreThan10Rentals();


    @Query("SELECT c.firstName, c.lastName, COUNT(DISTINCT ct.categoryId) " +
       "FROM Customer c " +
       "JOIN c.rentals r " +
       "JOIN r.inventory i " +
       "JOIN i.film f " +
       "JOIN f.filmCategories fc " +
       "JOIN fc.category ct " +
       "GROUP BY c.customerId " +
       "HAVING COUNT(DISTINCT ct.categoryId) = (SELECT COUNT(*) FROM Category)")
    List<Object> getCustomersWhoRentedAllCategories();


    @Query("SELECT DISTINCT c.firstName, c.lastName " +
       "FROM Customer c " +
       "JOIN c.rentals r " +
       "JOIN r.inventory i " +
       "JOIN i.film f " +
       "JOIN f.filmCategories fc " +
       "WHERE NOT EXISTS (SELECT 1 " +
       "FROM Rental r2 " +
       "JOIN r2.inventory i2 " +
       "JOIN i2.film f2 " +
       "JOIN f2.filmCategories fc2 " +
       "WHERE r2.customer.customerId = c.customerId " +
       "AND fc2.category = fc.category " +
       "AND r2.rentalDate < r.rentalDate) " +
       "AND c.customerId NOT IN (SELECT DISTINCT r3.customer.customerId " +
       "FROM Rental r3 " +
       "JOIN r3.inventory i3 " +
       "JOIN i3.film f3 " +
       "WHERE f3.length > 180)")
    List<Customer> getNewCategoryCustomersWithoutLongFilms();


}