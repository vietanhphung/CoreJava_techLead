package com.java_sql.main.controller.controller_sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java_sql.main.repository.ActorRepository;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/1.1")
    public List<Object> getAllActors() {
        return actorRepository.findAllActorsNames();
    }

    @GetMapping("/1.7")
    public List<Object> getActorsWithMoreThan20Films() {
        return actorRepository.findActorsWithMoreThan20Films();
    }

    @GetMapping("/2.4")
    public List<Object> getActorsInEveryCategory() {
        return actorRepository.findActorsInEveryCategory();
    }

    @GetMapping("/2.6")
    public List<Object> getRevenueByActor() {
        return actorRepository.findRevenueByActor();
    }

    @GetMapping("/2.7")
    public List<Object> getActorsOnlyInRRatedMovies() {
        return actorRepository.findActorsOnlyInRRatedMovies();
    }

    @GetMapping("/3.1")
    public List<Object> getAvgRentalDuration() {
        return actorRepository.getAvgRentalDuration();
    }

    @GetMapping("/3.2")
    public List<Object> getActorsInRFilmsNotG() {
        return actorRepository.getActorsInRFilmsNotG();
    }
/* 
    @GetMapping("/3.6")
    public List<Object> getActorsWhoAppearedWithAll() {
        return actorRepository.getActorsWhoAppearedWithAll();
    }
*/
    @GetMapping("/3.10")
    public List<Object> getActorsInSpecificFilms() {
        return actorRepository.getActorsInSpecificFilms();
    }
}
