package com.recime;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.recime.models.Recipe;

public class RecipeActivity extends FragmentActivity {
    private Recipe recipeObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);

        Intent intent = getIntent();
        String recipeId = intent.getStringExtra("recipeId");

        Bundle mArgs = new Bundle();
        mArgs.putString("recipeId", recipeId);

        if (savedInstanceState == null) {
            RecipeFragment recipeFragment = new RecipeFragment();
            recipeFragment.setArguments(mArgs);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, recipeFragment).commit();
        }



    }


}
