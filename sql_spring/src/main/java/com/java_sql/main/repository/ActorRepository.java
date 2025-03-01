package com.java_sql.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java_sql.main.entity.*;

import java.util.List;

@Repository
public interface ActorRepository  extends JpaRepository<Actor, Long> {

    @Query(value = "SELECT first_name, last_name FROM actor", nativeQuery = true)
List<Object> findAllActorsNames();




    @Query(value = """
        SELECT a.first_name, a.last_name, COUNT(fa.film_id) AS film_count
        FROM actor a
        JOIN film_actor fa ON a.actor_id = fa.actor_id
        GROUP BY a.actor_id
        HAVING COUNT(fa.film_id) > 20;
    """, nativeQuery = true)
    List<Object> findActorsWithMoreThan20Films();


    @Query(value = """
        SELECT a.first_name, a.last_name
        FROM actor a
        JOIN film_actor fa ON a.actor_id = fa.actor_id
        JOIN film_category fc ON fa.film_id = fc.film_id
        GROUP BY a.actor_id
        HAVING COUNT(DISTINCT fc.category_id) = (SELECT COUNT(*) FROM category);
    """, nativeQuery = true)
    List<Object> findActorsInEveryCategory();


    @Query(value = """
        SELECT a.first_name, a.last_name, SUM(p.amount) AS total_revenue
        FROM actor a
        JOIN film_actor fa ON a.actor_id = fa.actor_id
        JOIN inventory i ON fa.film_id = i.film_id
        JOIN rental r ON i.inventory_id = r.inventory_id
        JOIN payment p ON r.rental_id = p.rental_id
        GROUP BY a.actor_id
        ORDER BY total_revenue DESC;
    """, nativeQuery = true)
    List<Object> findRevenueByActor();

    @Query(value = """
        SELECT a.first_name, a.last_name
        FROM actor a
        JOIN film_actor fa ON a.actor_id = fa.actor_id
        JOIN film f ON fa.film_id = f.film_id
        WHERE f.rating = 'R'
        AND NOT EXISTS (
            SELECT 1
            FROM film_actor fa2
            JOIN film f2 ON fa2.film_id = f2.film_id
            WHERE fa2.actor_id = a.actor_id AND f2.rating = 'G'
        )
        GROUP BY a.actor_id;
    """, nativeQuery = true)
    List<Object> findActorsOnlyInRRatedMovies();


    @Query("SELECT  (a.actorId, a.firstName, a.lastName, c.name, AVG(DATEDIFF(r.returnDate, r.rentalDate))) " +
       "FROM Actor a " +
       "JOIN a.filmActors fa " +
       "JOIN fa.film f " +
       "JOIN f.filmCategories fc " +
       "JOIN fc.category c " +
       "JOIN f.inventories i " +
       "JOIN i.rentals r " +
       "WHERE r.returnDate IS NOT NULL " +
       "GROUP BY a.actorId, c.categoryId")
    List<Object[]> getAvgRentalDuration();


    @Query("SELECT DISTINCT (a.firstName, a.lastName) " +
       "FROM Actor a " +
       "JOIN a.filmActors fa " +
       "JOIN fa.film f " +
       "WHERE f.rating = 'R' AND f.length > 120 " +
       "AND a.actorId NOT IN (SELECT DISTINCT a2.actorId FROM Actor a2 " +
       "JOIN a2.filmActors fa2 JOIN fa2.film f2 WHERE f2.rating = 'G')")
    List<Object> getActorsInRFilmsNotG();





    @Query("SELECT DISTINCT (a.firstName, a.lastName) " +
       "FROM Actor a " +
       "JOIN a.filmActors fa_pg " +
       "JOIN fa_pg.film f_pg " +
       "JOIN a.filmActors fa_r " +
       "JOIN fa_r.film f_r " +
       "WHERE f_pg.rating = 'PG-13' AND f_pg.length > 120 " +
       "AND f_r.rating = 'R' AND f_r.length < 90")
        List<Object> getActorsInSpecificFilms();


}
