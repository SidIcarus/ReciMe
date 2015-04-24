package com.recime;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.recime.models.Ingredient;
import com.recime.models.Recipe;
import com.recime.models.RecipeStep;

import java.util.List;

public class RecipeFragment extends Fragment {
    private Recipe recipeObject;
    ListView recipeListView;
    ListView ingredientListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getActivity();
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.recipe_view, container, false);

        Bundle extras = getArguments();
        String recipeId = extras.getString("recipeId");
        recipeObject = Recipe.findById(Recipe.class, Long.parseLong(recipeId));

        TextView titleTextView = (TextView)V.findViewById(R.id.recipeName);
        titleTextView.setText(recipeObject.getName());

        List<RecipeStep> recipeSteps = recipeObject.getRecipeSteps();
        List<Ingredient> ingredientList = recipeObject.getIngredients();

        // Get ListView object from xml
        recipeListView = (ListView) V.findViewById(R.id.stepsList);
        ingredientListView = (ListView) V.findViewById(R.id.ingredientsList);

        recipeListView.setAdapter(new RecipeListAdapter(context, R.layout.recipe_step_row, recipeSteps));
        ingredientListView.setAdapter(new IngredientListAdapter(context, R.layout.ingredient_row, ingredientList));

        Button addToMealPlan = (Button)V.findViewById(R.id.addToMealPlan);
        addToMealPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MealPlanSingleton.getInstance().addRecipeToMealPlan(recipeObject);
                Toast.makeText(getActivity(), String.format("Added %s to meal plan", recipeObject.getName()),
                        Toast.LENGTH_LONG).show();
            }
        });

        Button addToShoppingList = (Button)V.findViewById(R.id.addToShoppingList);
        addToShoppingList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShoppingListSingleton.getInstance().addIngredientToShoppingList(recipeObject.getIngredients());
                Toast.makeText(getActivity(), String.format("Added %s's ingredients to shopping list", recipeObject.getName()),
                        Toast.LENGTH_LONG).show();
            }
        });

        return V;
    }

    public class RecipeListAdapter extends ArrayAdapter<RecipeStep> {

        public RecipeListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public RecipeListAdapter(Context context, int resource, List<RecipeStep> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.recipe_step_row, null);
            }

            RecipeStep recipeStep = getItem(position);

            if (recipeStep != null) {
                TextView tt = (TextView) v.findViewById(R.id.stepRow);
                tt.setText(recipeStep.getAction());

                TextView numOrder = (TextView) v.findViewById((R.id.numOrder));
                numOrder.setText(recipeStep.getStepNum().toString());
            }

            return v;

        }
    }

    public class IngredientListAdapter extends ArrayAdapter<Ingredient> {

        public IngredientListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public IngredientListAdapter(Context context, int resource, List<Ingredient> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.ingredient_row, null);
            }

            Ingredient ingredient = getItem(position);

            if (ingredient != null) {
                TextView tt = (TextView) v.findViewById(R.id.ingredientName);
                tt.setText(ingredient.getName());
            }

            return v;

        }
    }


}