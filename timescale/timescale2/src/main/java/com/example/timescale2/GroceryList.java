package com.example.timescale2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.example.timescale2.model.Grocery;

@Component
@XmlRootElement(name = "grocery")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroceryList {
    @XmlElement(name = "product")
    private List<Grocery> groceryList;

    public void setGroceryList(List < Grocery > groceryList){
        this.groceryList = groceryList;
    }

    public List<Grocery> getGroceryList(){
        return groceryList;
    }
}

