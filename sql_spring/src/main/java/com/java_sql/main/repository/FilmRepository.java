package com.java_sql.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java_sql.main.entity.*;

import java.util.List;

import jakarta.transaction.Transactional;

@Repository
public interface FilmRepository  extends JpaRepository<Film, Long>{

    @Query(value = "Select title , rental_rate, replacement_cost from film", nativeQuery = true)
    List<Object> findAllFilmTitleRateCost();

    @Query(value = "SELECT film.title, COUNT(rental.rental_id) AS rental_count FROM rental JOIN inventory ON rental.inventory_id = inventory.inventory_id JOIN film ON inventory.film_id = film.film_id GROUP BY film.title ORDER BY rental_count DESC LIMIT 5", nativeQuery = true)
    List<Object> find5MostRentedFilm();

    @Query("SELECT f.title FROM Film f WHERE f.rating = 'PG-13' AND f.length > 120")
    List<Object> findPg13FilmsOver120Minutes();

    @Query(value = """
        SELECT DISTINCT f.title
        FROM film f
        JOIN inventory i ON f.film_id = i.film_id
        JOIN rental r ON i.inventory_id = r.inventory_id
        WHERE r.return_date IS NULL;
    """, nativeQuery = true)
    List<Object> findFilmsNeverReturned();


    @Query(value = """
        SELECT f.title
        FROM rental r
        JOIN inventory i ON r.inventory_id = i.inventory_id
        JOIN film f ON i.film_id = f.film_id
        GROUP BY f.film_id
        HAVING COUNT(DISTINCT r.customer_id) > 50
        AND MAX(
            (SELECT COUNT(*) 
             FROM rental r2 
             WHERE r2.inventory_id = r.inventory_id 
             AND r2.customer_id = r.customer_id)
        ) = 1;
    """, nativeQuery = true)
    List<Object> findFilmsRentedByMoreThan50CustomersOnce();


    @Query(value = """
        SELECT f.title 
        FROM film f
        JOIN inventory i ON i.film_id = f.film_id
        JOIN rental r ON i.inventory_id = r.inventory_id
        WHERE r.customer_id IN (
            SELECT DISTINCT c.customer_id 
            FROM customer c
            JOIN rental r ON c.customer_id = r.customer_id
            JOIN inventory i ON i.inventory_id = r.inventory_id
            JOIN film_category fc ON fc.film_id = i.film_id
            JOIN category ct ON ct.category_id = fc.category_id
            WHERE LOWER(ct.name) = 'action'
        )
        GROUP BY f.film_id
        HAVING COUNT(DISTINCT r.customer_id) = (
            SELECT COUNT(DISTINCT c.customer_id) 
            FROM customer c
            JOIN rental r ON c.customer_id = r.customer_id
            JOIN inventory i ON i.inventory_id = r.inventory_id
            JOIN film_category fc ON fc.film_id = i.film_id
            JOIN category ct ON ct.category_id = fc.category_id
            WHERE LOWER(ct.name) = 'action'
        );
    """, nativeQuery = true)
    List<Object> findFilmsRentedByAllActionCustomers();



    @Query("SELECT (c.firstName, c.lastName, f.title, DATE(r.rentalDate), COUNT(r)) " +
       "FROM Customer c " +
       "JOIN c.rentals r " +
       "JOIN r.inventory i " +
       "JOIN i.film f " +
       "GROUP BY c.customerId, f.filmId, DATE(r.rentalDate) " +
       "HAVING COUNT(r) > 1")
    List<Object> getFrequentDailyRentals();

    @Query(value = "SELECT f.title FROM Film f " +
       "JOIN f.inventories i " +
       "JOIN i.rentals r " +
       "JOIN r.customer c " +
       "GROUP BY f.filmId " +
       "HAVING COUNT(r.rentalId) > 100 " +
       "AND f.filmId NOT IN (SELECT DISTINCT i2.film.filmId FROM Rental r2 " +
       "JOIN r2.inventory i2 JOIN i2.film f2 WHERE f2.rating = 'G' " +
       "AND r2.customer.customerId IN (SELECT DISTINCT r3.customer.customerId " +
       "FROM Rental r3 JOIN r3.inventory i3 JOIN i3.film f3 WHERE f3.rating = 'G'))", nativeQuery = true)
    List<Object> getPopularFilmsNotWatchedByGCustomers();





    //---------------UPDATE Queries---------------------

    @Query(value = "SET SQL_SAFE_UPDATES = 0;", nativeQuery = true)
    void disableSafeUpdates();

    @Query(value = "SET SQL_SAFE_UPDATES = 1;", nativeQuery = true)
    void enableSafeUpdates();

    @Modifying
    @Transactional
    @Query(value = 
           "UPDATE film f " +
           "JOIN ( " +
           "    SELECT f.film_id " +
           "    FROM film f " +
           "    JOIN inventory i ON f.film_id = i.film_id " +
           "    JOIN rental r ON i.inventory_id = r.inventory_id " +
           "    GROUP BY f.film_id " +
           "    HAVING COUNT(r.rental_id) > 100 " +
           ") rented_films ON f.film_id = rented_films.film_id " +
           "SET f.rental_rate = f.rental_rate * 1.10"
           , nativeQuery = true)
    void updateRentalRateForRentedFilmsMoreThan100Times();
    

    @Modifying
    @Transactional
    @Query(value = 
        "UPDATE film " +
        "JOIN ( " +
        "    SELECT f.film_id " +
        "    FROM film f " +
        "    JOIN inventory i ON f.film_id = i.film_id " +
        "    JOIN rental r ON i.inventory_id = r.inventory_id " +
        "    GROUP BY f.film_id " +
        "    HAVING COUNT(r.rental_id) > 5 " +
        ") AS rented_films ON film.film_id = rented_films.film_id " +
        "SET film.rental_duration = ROUND(film.rental_duration * 1.05); " 
        , nativeQuery = true)
        void updateRentalDurationForRentedFilmsMoreThan5Times();

        @Modifying
        @Transactional
        @Query(value = "UPDATE film f " +
            "JOIN film_category fc ON f.film_id = fc.film_id " +
            "JOIN category cat ON cat.category_id = fc.category_id " +
            "SET f.rental_rate = f.rental_rate * 1.20 " +
            "WHERE cat.name = 'Action' " +
            "AND f.release_year < 2005", nativeQuery = true)
        void updateRentalRateForActionFilmsBefore2005();
        



        

}