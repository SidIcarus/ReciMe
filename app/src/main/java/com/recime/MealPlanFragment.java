package com.recime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.recime.models.Ingredient;
import com.recime.models.Recipe;
import com.recime.models.RecipeStep;


public class MealPlanFragment extends Fragment {
    private List<String> numbers = Arrays.asList("one", "two", "three", "four", "five");
    private List<String> recipes = Arrays.asList("Soup", "Bread", "Fish & Chips", "Burgers", "Eggs");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.meal_plan_view, container, false);

        TextView displayTextView = (TextView)V.findViewById(R.id.shoppingTitle);
        displayTextView.setText("OMG IT WORKED");

        Recipe.deleteAll(Recipe.class);
        RecipeStep.deleteAll(RecipeStep.class);
        for (String rec : recipes ){
            Recipe recipe = new Recipe(rec);
            recipe.save();
            for (Integer i = 0; i < numbers.size(); i++) {
                RecipeStep recstep = new RecipeStep(recipe, i + 1, numbers.get(i), i/(float)2.0);
                Ingredient ingredient = new Ingredient(i.toString() + " " + recipe.getName());
                ingredient.save();
                recstep.save();
                recipe.addIngredient(ingredient);
            }
            List<Ingredient> ingredients = recipe.getIngredients();
            System.out.println("-------------------");
            for (Ingredient rec1 : ingredients) {
                System.out.println(rec1.getName());
            }
            System.out.println("-------------------");

            MealPlanSingleton.getInstance().addRecipeToMealPlan(recipe);
        }




        return V;
    }




}