package com.example.product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource(collectionResourceRel = "grocery", path = "grocery")
public interface GroceryRepository extends MongoRepository<Grocery, String> {
    List<Grocery> findAll();
    List<Grocery> findByName(@Param("name") String name);
}


