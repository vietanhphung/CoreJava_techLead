package com.java_sql.main.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


    @OneToMany(mappedBy = "inventory")
    private List<Rental> rentals;
    // Getters and Setters
}