package com.java_sql.main.controller.controller_sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java_sql.main.repository.ActorRepository;
import com.java_sql.main.dto.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/1.1")
    public List<FindAllActorsNamesDTO> getAllActors() {
        return actorRepository.findAllActorsNames()
                .stream()
                .map(obj -> {
                    Object[] row = (Object[]) obj;
                    return new FindAllActorsNamesDTO(
                        row[0].toString(),  // First Name
                        row[1].toString()   // Last Name
                    );
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/1.7")
    public List<ActorWithFilmCountDTO> getActorsWithMoreThan20Films() {
        return actorRepository.findActorsWithMoreThan20Films()
                .stream()
                .map(obj -> {
                    Object[] row = (Object[]) obj;
                    return new ActorWithFilmCountDTO(
                        row[0].toString(),  // First Name
                        row[1].toString(),  // Last Name
                        ((Number) row[2]).intValue() // Film Count
                    );
                })
                .collect(Collectors.toList());
    }



    @GetMapping("/2.6")
    public List<ActorRevenueDTO> getRevenueByActor() {
        return actorRepository.findRevenueByActor()
                .stream()
                .map(obj -> {
                    Object[] row = (Object[]) obj;
                    return new ActorRevenueDTO(
                        row[0].toString(),  // First Name
                        row[1].toString(),  // Last Name
                        ((Number) row[2]).intValue() // Film Count
                    );
                })
                .collect(Collectors.toList());
    }


    @GetMapping("/2.7")
    public List<ActorInRMoviesDTO> getActorsOnlyInRRatedMovies() {
        return actorRepository.findActorsOnlyInRRatedMovies()
        .stream()
                .map(obj -> {
                    Object[] row = (Object[]) obj;
                    return new ActorInRMoviesDTO(
                        row[0].toString(),  // First Name
                        row[1].toString()  // Last Name
                    );
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/3.1")
    public List<ActorAvgRentalDurationDTO> getAvgRentalDuration() {
        return actorRepository.getAvgRentalDuration()
                .stream()
                .map(row -> new ActorAvgRentalDurationDTO(
                        row[1].toString(),  // First Name
                        row[2].toString(),  // Last Name
                        row[3].toString(),  // Category Name
                        ((Number) row[4]).doubleValue() // Avg Rental Duration
                ))
                .collect(Collectors.toList());
    }
    
    

    @GetMapping("/3.2")
    public List<FindAllActorsNamesDTO> getActorsInRFilmsNotG() {
        return actorRepository.getActorsInRFilmsNotG()
                .stream()
                .map(obj -> {
                    Object[] row = (Object[]) obj;
                    return new FindAllActorsNamesDTO(
                        row[0].toString(),  // First Name
                        row[1].toString()   // Last Name
                    );
                })
                .collect(Collectors.toList());
    }
    

    @GetMapping("/3.10")
    public List<FindAllActorsNamesDTO> getActorsInSpecificFilms() {
        return actorRepository.getActorsInSpecificFilms()
        .stream()
        .map(obj -> {
            Object[] row = (Object[]) obj;
            return new FindAllActorsNamesDTO(
                row[0].toString(),  // First Name
                row[1].toString()   // Last Name
            );
        })
        .collect(Collectors.toList());

    }
}
