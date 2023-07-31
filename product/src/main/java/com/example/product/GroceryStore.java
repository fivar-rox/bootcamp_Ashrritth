package com.example.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@Component
public class GroceryStore {
    private static final String STORE_XML = "/Users/pashrritth/Documents/formalities/bootcamp/product/src/main/java/com/example/product/products.xml";

    @Autowired
    private GroceryRepository groceryRepo;

    // @Autowired 
    // private GroceryTimescale timescaleRepo;

    @Autowired 
    private Environment environment;

    @PostConstruct
    public void fillDatabase() throws JAXBException, IOException{
        List< Grocery >  groceryList = new ArrayList<Grocery>();
        Grocery grocery1 = new Grocery();
        grocery1.setDescription("hey I am new here");
        grocery1.setName("Oppenhiemer");
        grocery1.setPrice(110000);
        
        groceryList.add(grocery1);

        List<Grocery> final_list = convertXMLToObject().getGroceryList();

        groceryList.addAll(final_list);
        //if(environment.getActiveProfiles()[0].equals("mongo")){
            groceryRepo.saveAll(groceryList);
        // }
        //     else{
                // timescaleRepo.saveAll(groceryList);
        // }
    }

    private static GroceryList convertXMLToObject(){
        try{
            JAXBContext context = JAXBContext.newInstance(GroceryList.class);
            Unmarshaller un = context.createUnmarshaller();
            GroceryList groceryList1 = (GroceryList) un.unmarshal(new File(STORE_XML));

            return groceryList1;
        }
        catch(JAXBException e){ 
            e.printStackTrace();
        }
        return null;
    }

}
