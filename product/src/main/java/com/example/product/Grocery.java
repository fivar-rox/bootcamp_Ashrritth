package com.example.product;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

 import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "grocery")
@XmlRootElement(name = "product")
@XmlType(propOrder = {
    "id",
    "name",
    "price",
    "description"
})
public class Grocery {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice()
    { return price;}
    public void setPrice(double price){ this.price = price;}
    public String getDescription(){ return description;}
    public void setDescription(String description){this.description = description;}
}
