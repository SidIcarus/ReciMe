package com.recime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class MealPlanFragment extends Fragment {
    private TextView displayTextView;
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
                RecipeStep recstep = new RecipeStep(recipe, i + 1, numbers.get(i));
                recstep.save();
            }
            List<RecipeStep> recsteps = recipe.getRecipeSteps();
            for (RecipeStep rec1 : recsteps) {
                System.out.println("-------------------");
                System.out.println(rec1.recipe.name);
                System.out.println(rec1.stepNum);
                System.out.println(rec1.action);
                System.out.println("-------------------");
            }
        }




        return V;
    }


}