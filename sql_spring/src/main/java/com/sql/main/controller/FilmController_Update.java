package com.sql.main.controller;

import com.sql.main.service.FilmService_Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/updateQuery")
public class FilmController_Update {

    @Autowired
    private FilmService_Update filmService;

    // Endpoint to update rental rate for films rented more than 100 times
    @PostMapping("/updateRentalRateForRentedFilmsMoreThan100Times")
    public void updateRentalRateForRentedFilmsMoreThan100Times() {
         filmService.updateRentalRateForRentedFilmsMoreThan100Times();
    }

    // Endpoint to update rental duration for films rented more than 5 times
    @PostMapping("/updateRentalDurationForRentedFilmsMoreThan5Times")
    public void updateRentalDurationForRentedFilmsMoreThan5Times() {
        filmService.updateRentalDurationForRentedFilmsMoreThan5Times();
    }

    // Endpoint to update rental rate for action films released before 2005
    @PostMapping("/updateRentalRateForActionFilmsBefore2005")
    public void updateRentalRateForActionFilmsBefore2005() {
        filmService.updateRentalRateForActionFilmsBefore2005();
    }

}
