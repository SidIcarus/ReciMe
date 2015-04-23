package com.recime;

import java.util.ArrayList;
import com.recime.models.Ingredient;

public class ShoppingListSingleton {

    private static ShoppingListSingleton instance = null;
    private ArrayList<Ingredient> shoppingList = new ArrayList<>();

    protected ShoppingListSingleton() {
        // Exists only to defeat instantiation.
    }
    public static ShoppingListSingleton getInstance() {
        if(instance == null) {
            instance = new ShoppingListSingleton();
        }
        return instance;
    }

    public ArrayList<Ingredient> getMealPlanRecipes() {
        return shoppingList;
    }

    public void addRecipeToMealPlan(Ingredient ingredient) {
        shoppingList.add(ingredient);
    }

    public void removeRecipeFromMealPlan(Ingredient ingredient) {
        shoppingList.remove(shoppingList.indexOf(ingredient));
    }

}