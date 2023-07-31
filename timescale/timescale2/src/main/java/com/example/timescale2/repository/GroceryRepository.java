package com.example.timescale2.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.timescale2.model.Grocery;

@Component
@Repository
public interface GroceryRepository extends JpaRepository<Grocery, String> {
    // check the repository dependency here. Later.
    // if it can be done like this, may be we can integrate the whole thing.
    List<Grocery> findAll();
    
}
