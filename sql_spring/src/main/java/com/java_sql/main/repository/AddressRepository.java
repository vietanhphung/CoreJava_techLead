package com.java_sql.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java_sql.main.entity.*;

import java.util.List;

@Repository
public interface AddressRepository  extends JpaRepository<Customer, Long>{
    
}