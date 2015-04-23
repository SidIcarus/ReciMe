package com.recime;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    // Fragment TabHost as mTabHost
    private FragmentTabHost mTabHost;

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

        HashMap<String, List<Tuple<String, Integer>>> recipes = getRecipes();
        for (Map.Entry<String, List<Tuple<String, Integer>>> entry : recipes.entrySet())
        {
            String recipeName = entry.getKey();
            List<Tuple<String, Integer>> recipeSteps = entry.getValue();

            Recipe recipe = new Recipe(recipeName);
            recipe.save();

            System.out.println("-------------------");
            System.out.println(recipe.name);

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


                System.out.println(rec1.stepNum);
                System.out.println(rec1.stepTime);
                System.out.println("------------");
            }
        }
    }

    private HashMap<String, List<Tuple<String, Integer>>> _recipes = null;

    private HashMap<String, List<Tuple<String, Integer>>> getRecipes()
    {
        if (_recipes == null) {
            _recipes = new HashMap<String, List<Tuple<String, Integer>>>();

            ArrayList<Tuple<String, Integer>> steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Put pan on Medium-High heat", 0));
            steps.add(new Tuple<String, Integer>("Butter the Pan", 0));
            steps.add(new Tuple<String, Integer>("Prepare Eggs", 1));
            steps.add(new Tuple<String, Integer>("Cook Eggs", 5));
            steps.add(new Tuple<String, Integer>("Remove Eggs from pan", 0));
            steps.add(new Tuple<String, Integer>("Cook Bacon", 10));
            _recipes.put("Eggs and Bacon", steps);

            steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Add water to mug", 0));
            steps.add(new Tuple<String, Integer>("Heat water in microwave", 1));
            steps.add(new Tuple<String, Integer>("Stir in chocolate mix", 1));
            steps.add(new Tuple<String, Integer>("Wait for it to cool", 1));
            _recipes.put("Hot Chocolate", steps);

            steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Add ingredients to Ice Cream Machine", 0));
            steps.add(new Tuple<String, Integer>("Turn on Ice Cream Machine", 5));
            _recipes.put("Homemade Ice Cream", steps);

            steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Add pot of water to stove", 0));
            steps.add(new Tuple<String, Integer>("Boil Water", 5));
            steps.add(new Tuple<String, Integer>("Add Frankfurters to pot", 0));
            steps.add(new Tuple<String, Integer>("Cook Frankfurters until they float", 5));
            _recipes.put("Frankfurters", steps);

            steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Go to liquor store", 20));
            steps.add(new Tuple<String, Integer>("Purchase wine", 5));
            steps.add(new Tuple<String, Integer>("Drive home", 5));
            _recipes.put("Homemade Wine", steps);

            steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Gather Ingredients", 0));
            steps.add(new Tuple<String, Integer>("Preheat oven to 350", 0));
            steps.add(new Tuple<String, Integer>("Mix Dry Ingredients", 2));
            steps.add(new Tuple<String, Integer>("Mix Wet Ingredients in separate bowls", 2));
            steps.add(new Tuple<String, Integer>("Combine bowls", 2));
            steps.add(new Tuple<String, Integer>("Spray pans", 0));
            steps.add(new Tuple<String, Integer>("Pour mixture into pans", 0));
            steps.add(new Tuple<String, Integer>("Bake cupcakes", 15));
            _recipes.put("Cupcakes", steps);

            steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Add water to mug", 0));
            steps.add(new Tuple<String, Integer>("Heat water in microwave", 1));
            steps.add(new Tuple<String, Integer>("Add teabag, and steep", 2));
            _recipes.put("Homemade Green Tea", steps);

            steps = new ArrayList<Tuple<String, Integer>>();
            steps.add(new Tuple<String, Integer>("Preheat oven to 375", 0));
            steps.add(new Tuple<String, Integer>("Cut potatoes", 10));
            steps.add(new Tuple<String, Integer>("Place potatoes on pan", 0));
            steps.add(new Tuple<String, Integer>("Sprinkle seasoning over potatoes", 0));
            steps.add(new Tuple<String, Integer>("Bake potatoes in oven", 25));
            _recipes.put("Steak Fries", steps);
        }
        return _recipes;
    }
}
