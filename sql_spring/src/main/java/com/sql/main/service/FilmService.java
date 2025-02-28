package com.sql.main.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.sql.main.repository.*;




@Service
public class FilmService {
    //------------- Film ------------------
    @Autowired
    private FilmRepository filmRepository;
    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public List<Object> getAllFilmTitleRateCost() {
        return filmRepository.findAllFilmTitleRateCost();
    }

    public List<Object> get5MostRentedFilms() {
        return filmRepository.find5MostRentedFilm();
    }

    public List<Object> getPg13FilmsOver120Minutes() {
        return filmRepository.findPg13FilmsOver120Minutes();
    }

    public List<Object> getFilmsNeverReturned() {
        return filmRepository.findFilmsNeverReturned();
    }

    public List<Object> getFilmsRentedByMoreThan50CustomersOnce() {
        return filmRepository.findFilmsRentedByMoreThan50CustomersOnce();
    }

    public List<Object> getFilmsRentedByAllActionCustomers() {
        return filmRepository.findFilmsRentedByAllActionCustomers();
    }

    public List<Object> getFrequentDailyRentals() {
        return filmRepository.getFrequentDailyRentals();
    }

    public List<Object> getPopularFilmsNotWatchedByGCustomers() {
        return filmRepository.getPopularFilmsNotWatchedByGCustomers();
    }





}