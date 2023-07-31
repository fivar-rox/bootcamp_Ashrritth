package com.example.timescale2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.timescale2.model.Grocery;
import com.example.timescale2.repository.GroceryRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/grocery")
public class TimescaleController {
    @Autowired
    private GroceryRepository timescaleRepo;

    // --------------------TIMESCALE CONTROLLERS-----------------------
    @GetMapping("/findAllItems")
    public ResponseEntity<List<Grocery>> getTimescaleGrocery(){
        System.out.println("Trying timescale findAllItems");
        try {
            return new ResponseEntity<>(timescaleRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getTimescaleGrocery(@PathVariable("id") String id){
        try {
            //check if employee exist in database
            Grocery empObj = getGroceryRecord(id);

            if (empObj != null) {
                return new ResponseEntity<>(empObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addgrocery")
    public ResponseEntity<Grocery> addNewGrocery(@RequestBody Grocery grocery) {
        Grocery newGrocery = timescaleRepo
                .save(Grocery.builder()
                        .name(grocery.getName())
                        .price(grocery.getPrice())
                        .description(grocery.getDescription())
                        .build());
        return new ResponseEntity<>(newGrocery, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Grocery> updateEmployee(@PathVariable("id") String id, @RequestBody Grocery employee) {

        //check if employee exist in database
        Grocery empObj = getGroceryRecord(id);

        if (empObj != null) {
            empObj.setName(employee.getName());
            empObj.setPrice(employee.getPrice());
            empObj.setDescription(employee.getDescription());
            return new ResponseEntity<>(timescaleRepo.save(empObj), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") String id) {
        try {
            //check if employee exist in database
            Grocery emp = getGroceryRecord(id);

            if (emp != null) {
                timescaleRepo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllGrocery(){
        try {
            //check if employee exist in database
                timescaleRepo.deleteAllInBatch();
                return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // --------------- FINDING INDIVIDUAL ELEMENT ------------------
    private Grocery getGroceryRecord(String id) {
        Optional<Grocery> empObj = timescaleRepo.findById(id);

        if (empObj.isPresent()) {
            return empObj.get();
        }
        return null;
    }

}
