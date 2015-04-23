package com.recime;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void addIngredientToShoppingList(Ingredient ingredient) {
        shoppingList.add(ingredient);
    }

    public void addIngredientToShoppingList(List<Ingredient> ingredientList) {
        for (Ingredient ingredient : ingredientList) {
            getInstance().addIngredientToShoppingList(ingredient);
        }
    }

    public void removeIngredientFromShoppingList(Ingredient ingredient) {
        shoppingList.remove(shoppingList.indexOf(ingredient));
    }

}