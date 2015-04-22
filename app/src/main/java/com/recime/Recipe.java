package com.recime;

import com.orm.SugarRecord;

public class Recipe extends SugarRecord<Recipe> {
    String name;

    public Recipe(){
    }

    public Recipe(String name){
        this.name = name;
    }
}
