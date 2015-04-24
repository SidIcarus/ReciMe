package com.recime;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.recime.models.Recipe;
import com.recime.models.RecipeStep;
import com.recime.models.Ingredient;

public class MainActivity extends FragmentActivity {
    // Fragment TabHost as mTabHost
    private FragmentTabHost mTabHost;

    EditText textSearchField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitRecipesAndSteps();

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("recipes").setIndicator("Recipes"),
                RecipeListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("shoppinglist").setIndicator("Shopping List"),
                ShoppingListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mealplan").setIndicator("Meal Plan"),
                MealPlanFragment.class, null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class Tuple<X, Y> {
        public final X x;
        public final Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    public void InitRecipesAndSteps()
    {
        Recipe.deleteAll(Recipe.class);
        RecipeStep.deleteAll(RecipeStep.class);

        HashMap<String, Tuple<List<Ingredient>, List<Tuple<String, Integer>>>> recipes = getRecipes();
        for (Map.Entry<String, Tuple<List<Ingredient>, List<Tuple<String, Integer>>>> entry : recipes.entrySet())
        {
            String recipeName = entry.getKey();
            Tuple<List<Ingredient>, List<Tuple<String, Integer>>> layer1 = entry.getValue();
            List<Tuple<String, Integer>> recipeSteps = layer1.y;

            Recipe recipe = new Recipe(recipeName);
            recipe.save();

            System.out.println("-------------------");
            System.out.println(recipe.getName());

            for (Integer i = 0; i < recipeSteps.size(); i++)
            {
                Tuple<String, Integer> step = recipeSteps.get(i);
                String action = step.x;
                Float time = (float) step.y;
                RecipeStep recstep = new RecipeStep(recipe, i + 1, action, time);
                recstep.save();
            }

            System.out.println("Steps:");

            List<RecipeStep> recsteps = recipe.getRecipeSteps();
            for (RecipeStep rec1 : recsteps) {
                System.out.println(rec1.getStepNum());
                System.out.println(rec1.getStepTime());
                System.out.println("------------");
            }

            List<Ingredient> recipeIngredients = layer1.x;

            for (Ingredient ing: recipeIngredients)
            {
                recipe.addIngredient((ing));
            }
        }

        List<Recipe> recs = Recipe.listAll(Recipe.class);
        MealPlanSingleton.getInstance().addRecipeToMealPlan(recs.get(0));
    }

    private HashMap<String, Tuple<List<Ingredient>, List<Tuple<String, Integer>>>> _recipes = null;

    private HashMap<String, Tuple<List<Ingredient>, List<Tuple<String, Integer>>>> getRecipes()
    {
        if (_recipes == null) {
            _recipes = new HashMap<String, Tuple<List<Ingredient>, List<Tuple<String, Integer>>>>();

            Ingredient butter = new Ingredient("Butter", "Refrigerated Aisle", 0);
            butter.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(butter);
            Ingredient eggs = new Ingredient("Eggs", "Refrigerated Aisle", randInt(0, 70));
            eggs.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(eggs);
            Ingredient bacon = new Ingredient("Bacon", "Refrigerated Aisle", randInt(0, 70));
            bacon.save();
            Ingredient milk = new Ingredient("Milk", "Refrigerated Aisle", randInt(0, 70));
            milk.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(milk);
            Ingredient cheese = new Ingredient("Cheese", "Refrigerated Aisle", randInt(0, 70));
            cheese.save();
            Ingredient hotchocolate = new Ingredient("Hot Chocolate", "Aisle 5", randInt(0, 70));
            hotchocolate.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(hotchocolate);
            Ingredient icecream = new Ingredient("Ice Cream Mix", "Aisle 15", randInt(0, 70));
            icecream.save();
            Ingredient wine = new Ingredient("Wine", "Aisle 10", randInt(0, 70));
            wine.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(wine);
            Ingredient cupcakemix = new Ingredient("Cupcake Mix", "Aisle 3", randInt(0, 70));
            cupcakemix.save();
            Ingredient greenteabag = new Ingredient("Green Tea Bags", "Aisle 5", randInt(0, 70));
            greenteabag.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(greenteabag);
            Ingredient blackteabag = new Ingredient("Black Tea Bags", "Aisle 5", randInt(0, 70));
            blackteabag.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(blackteabag);
            Ingredient pam = new Ingredient("Pam", "Aisle 3", randInt(0, 70));
            pam.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(pam);
            Ingredient potatoes = new Ingredient("Potatoes", "Aisle 1", randInt(0, 70));
            potatoes.save();
            Ingredient hotdogs = new Ingredient("Hot Dogs", "Aisle 8", randInt(0, 70));
            hotdogs.save();
            Ingredient salt = new Ingredient("Salt", "Aisle 2", randInt(0, 70));
            salt.save();
            Ingredient pepper = new Ingredient("Pepper", "Aisle 2", randInt(0, 70));
            pepper.save();
            ShoppingListSingleton.getInstance().addIngredientToShoppingList(pepper);
            Ingredient parsley = new Ingredient("Parsley", "Aisle 2", randInt(0, 70));
            parsley.save();
            Ingredient sage = new Ingredient("Sage", "Aisle 2", randInt(0, 70));
            sage.save();
            Ingredient rosemary = new Ingredient("Rosemary", "Aisle 2", randInt(0, 70));
            rosemary.save();
            Ingredient thyme = new Ingredient("Thyme", "Aisle 2", randInt(0, 70));
            thyme.save();

            ArrayList<Tuple<String, Integer>> steps = new ArrayList<Tuple<String, Integer>>();
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Put pan on Medium-High heat", 0));
            steps.add(new Tuple<String, Integer>("Butter the Pan", 0));
            steps.add(new Tuple<String, Integer>("Prepare Eggs", 1));
            steps.add(new Tuple<String, Integer>("Cook Eggs", 5));
            steps.add(new Tuple<String, Integer>("Remove Eggs from pan", 0));
            steps.add(new Tuple<String, Integer>("Cook Bacon", 10));
            ingredients.add(pam);
            ingredients.add(eggs);
            ingredients.add(bacon);
            _recipes.put("Eggs and Bacon", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));

            steps = new ArrayList<Tuple<String, Integer>>();
            ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Add water to mug", 0));
            steps.add(new Tuple<String, Integer>("Heat water in microwave", 1));
            steps.add(new Tuple<String, Integer>("Stir in chocolate mix", 1));
            steps.add(new Tuple<String, Integer>("Wait for it to cool", 1));
            ingredients.add(hotchocolate);
            _recipes.put("Hot Chocolate", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));

            steps = new ArrayList<Tuple<String, Integer>>();
            ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Add ingredients to Ice Cream Machine", 0));
            steps.add(new Tuple<String, Integer>("Turn on Ice Cream Machine", 5));
            ingredients.add(icecream);
            _recipes.put("Homemade Ice Cream", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));

            steps = new ArrayList<Tuple<String, Integer>>();
            ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Add pot of water to stove", 0));
            steps.add(new Tuple<String, Integer>("Boil Water", 5));
            steps.add(new Tuple<String, Integer>("Add Frankfurters to pot", 0));
            steps.add(new Tuple<String, Integer>("Cook Frankfurters until they float", 5));
            _recipes.put("Frankfurters", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));

            steps = new ArrayList<Tuple<String, Integer>>();
            ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Go to liquor store", 20));
            steps.add(new Tuple<String, Integer>("Purchase wine", 5));
            steps.add(new Tuple<String, Integer>("Drive home", 5));
            ingredients.add(wine);
            _recipes.put("Homemade Wine", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));

            steps = new ArrayList<Tuple<String, Integer>>();
            ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Gather Ingredients", 0));
            steps.add(new Tuple<String, Integer>("Preheat oven to 350", 0));
            steps.add(new Tuple<String, Integer>("Mix Dry Ingredients", 2));
            steps.add(new Tuple<String, Integer>("Mix Wet Ingredients in separate bowls", 2));
            steps.add(new Tuple<String, Integer>("Combine bowls", 2));
            steps.add(new Tuple<String, Integer>("Spray pans", 0));
            steps.add(new Tuple<String, Integer>("Pour mixture into pans", 0));
            steps.add(new Tuple<String, Integer>("Bake cupcakes", 15));
            ingredients.add(pam);
            ingredients.add(butter);
            ingredients.add(eggs);
            ingredients.add(milk);
            ingredients.add(cupcakemix);
            ingredients.add(salt);
            _recipes.put("Cupcakes", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));

            steps = new ArrayList<Tuple<String, Integer>>();
            ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Add water to mug", 0));
            steps.add(new Tuple<String, Integer>("Heat water in microwave", 1));
            steps.add(new Tuple<String, Integer>("Add teabag, and steep", 2));
            ingredients.add(greenteabag);
            _recipes.put("Homemade Green Tea", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));

            steps = new ArrayList<Tuple<String, Integer>>();
            ingredients = new ArrayList<Ingredient>();
            steps.add(new Tuple<String, Integer>("Preheat oven to 375", 0));
            steps.add(new Tuple<String, Integer>("Cut potatoes", 10));
            steps.add(new Tuple<String, Integer>("Place potatoes on pan", 0));
            steps.add(new Tuple<String, Integer>("Sprinkle seasoning over potatoes", 0));
            steps.add(new Tuple<String, Integer>("Bake potatoes in oven", 25));
            ingredients.add(potatoes);
            ingredients.add(salt);
            ingredients.add(pepper);
            _recipes.put("Steak Fries", new Tuple<List<Ingredient>, List<Tuple<String, Integer>>>(ingredients, steps));
        }
        return _recipes;
    }

    public static Random _rand = null;
    public static int randInt(int min, int max) {
        if (_rand == null)
            _rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = _rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
