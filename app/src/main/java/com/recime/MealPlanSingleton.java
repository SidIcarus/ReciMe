package com.recime;

import java.util.ArrayList;
import com.recime.Recipe;

public class MealPlanSingleton {

    private static MealPlanSingleton instance = null;
    private ArrayList<Recipe> mealPlanRecipes = new ArrayList<>();

    protected MealPlanSingleton() {
        // Exists only to defeat instantiation.
    }
    public static MealPlanSingleton getInstance() {
        if(instance == null) {
            instance = new MealPlanSingleton();
        }
        return instance;
    }

    public ArrayList<Recipe> getMealPlanRecipes() {
        return mealPlanRecipes;
    }

    public void addRecipeToMealPlan(Recipe recipe) {
        mealPlanRecipes.add(recipe);
    }

    public void removeRecipeFromMealPlan(Recipe recipe) {
        mealPlanRecipes.remove(mealPlanRecipes.indexOf(recipe));
    }

}