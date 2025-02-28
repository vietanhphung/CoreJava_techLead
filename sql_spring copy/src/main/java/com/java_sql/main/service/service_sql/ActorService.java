package com.java_sql.main.service.service_sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_sql.main.repository.*;

import java.util.List;




@Service
public class ActorService {
   

    //------------- Actor ------------------
    @Autowired
    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    public List<Object> getAllActorsNames() {
        return actorRepository.findAllActorsNames();
    }

    public List<Object> getActorsWithMoreThan20Films() {
        return actorRepository.findActorsWithMoreThan20Films();
    }

    public List<Object> getActorsInEveryCategory() {
        return actorRepository.findActorsInEveryCategory();
    }

    public List<Object> getRevenueByActor() {
        return actorRepository.findRevenueByActor();
    }

    public List<Object> getActorsOnlyInRRatedMovies() {
        return actorRepository.findActorsOnlyInRRatedMovies();
    }

    public List<Object> getAvgRentalDuration() {
        return actorRepository.getAvgRentalDuration();
    }

    public List<Object> getActorsInRFilmsNotG() {
        return actorRepository.getActorsInRFilmsNotG();
    }/*
    public List<Object> getActorsWhoAppearedWithAll() {
        return actorRepository.getActorsWhoAppearedWithAll();
    } */

    public List<Object> getActorsInSpecificFilms() {
        return actorRepository.getActorsInSpecificFilms();
    }






}


