package com.java_sql.main.service.service_sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java_sql.main.repository.*;

@Service
public class FilmService_Update {

    @Autowired
    private FilmRepository filmRepository;

    // Update rental rate for films rented more than 100 times
    @Transactional
    public void updateRentalRateForRentedFilmsMoreThan100Times() {
        try{
        //filmRepository.disableSafeUpdates(); 
        filmRepository.updateRentalRateForRentedFilmsMoreThan100Times();  // Perform the update
        //filmRepository.enableSafeUpdates();
        }
        catch (Exception e){
            System.out.println("Error********");
        }
    }

    // Update rental duration for films rented more than 5 times
    @Transactional
    public void updateRentalDurationForRentedFilmsMoreThan5Times() {
    try {
        //filmRepository.disableSafeUpdates(); 
        filmRepository.updateRentalDurationForRentedFilmsMoreThan5Times();
        //filmRepository.enableSafeUpdates();
        System.out.println("Success++++++++++");
    } catch (Exception e) {
        System.err.println("Error********: " + e.getMessage());
        e.printStackTrace();
    }
}

    // Update rental rate for action films released before 2005
    @Transactional
    public void updateRentalRateForActionFilmsBefore2005() {
        try {
            filmRepository.updateRentalRateForActionFilmsBefore2005();
            System.out.println("Success++++++++++");
        } catch (Exception e) {
            System.err.println("Error********: " + e.getMessage());
            e.printStackTrace();  // Print full error details
        }
    }
    

}
