package com.recime;

import com.orm.SugarRecord;

public class Ingredient extends SugarRecord<Ingredient> {
    String name;
    String aisle;
    Integer numInStock;

    public Ingredient(){}

    public Ingredient(String name) {
        this.name = name;
    }

}
