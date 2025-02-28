package com.sql.main.service;

import com.sql.main.entity.Customer;
import com.sql.main.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Object> getCustomersWhoRentedInJan2022() {
        return customerRepository.findCustomersWhoRentedInJan2022();
    }

    public List<Object> getTop10CustomersByRevenue() {
        return customerRepository.findTop10CustomersByRevenue();
    }

    public List<Object> getCustomersWhoRentedAllCategories() {
        return customerRepository.findCustomersWhoRentedAllCategories();
    }

    public List<Object> getCustomersWhoRentedSameFilmMultipleTimes() {
        return customerRepository.findCustomersWhoRentedSameFilmMultipleTimes();
    }

    public List<Object> getCustomersWhoRentedNewCategory() {
        return customerRepository.findCustomersWhoRentedNewCategory();
    }

    public List<Object> getCustomersWithMoreThan10Rentals() {
        return customerRepository.getCustomersWithMoreThan10Rentals();
    }

  
    public List<Object> getCustomersWhoRentedAllCategoriesDTO() {
        return customerRepository.getCustomersWhoRentedAllCategories();
    }

    public List<Customer> getNewCategoryCustomersWithoutLongFilms() {
        return customerRepository.getNewCategoryCustomersWithoutLongFilms();
    }
}
