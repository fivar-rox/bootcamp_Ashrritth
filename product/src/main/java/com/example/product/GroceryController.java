package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grocery")
@Profile("mongo")
public class GroceryController {
    @Autowired
    private GroceryRepository repo;

    @Profile("mongo")
    @PostMapping("/addgrocery")
    public String saveGrocery(@RequestBody Grocery grocery){
        repo.save(grocery);
        return "Added successfully";
    }

    @Profile("mongo")
    @GetMapping("/{id}")
    ResponseEntity<?> getGroup(@PathVariable String id) {
        Optional<Grocery> group = repo.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Profile("mongo")
    @GetMapping("/findAllItems")
    public List<Grocery> getGrocery(){
        System.out.println("Mongo /findAllItems has been triggered");
        return repo.findAll();
    }

    @Profile("mongo")
    @PutMapping("/{id}")
    ResponseEntity<Grocery> updateGroup(@RequestBody Grocery group) {
        // Console.log.info("Request to update group: {}", group);
        Grocery result = repo.save(group);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGrocery(@PathVariable String id){
        repo.deleteById(id);
        return "Deleted Successfully";
    }

    @DeleteMapping("/deleteAll")
    public String deleteGroceryAll(){
        repo.deleteAll();
        return "Deleted Successfully";
    }

    


}
