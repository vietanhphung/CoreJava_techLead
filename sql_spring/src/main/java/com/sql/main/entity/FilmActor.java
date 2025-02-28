package com.sql.main.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
public class FilmActor {

    @EmbeddedId
    private FilmActorId id;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    private Actor actor;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    // Constructors
    public FilmActor() {}

    public FilmActor(FilmActorId id, Actor actor, Film film, LocalDateTime lastUpdate) {
        this.id = id;
        this.actor = actor;
        this.film = film;
        this.lastUpdate = lastUpdate;
    }

    // Getters and Setters
    public FilmActorId getId() {
        return id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
