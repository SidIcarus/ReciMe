package com.recime.models;

import com.orm.SugarRecord;

public class Ingredient extends SugarRecord<Ingredient> {
    String name;
    String aisle;
    Integer numInStock;

    public Ingredient(){}

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    
    public String getAisle(){ return aisle; }

    public Integer getNumInStock() { return numInStock; }
}
