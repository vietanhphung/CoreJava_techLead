package com.sql.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.sql.main.entity.*;

@Repository
public interface AddressRepository  extends JpaRepository<Customer, Long>{
    
}