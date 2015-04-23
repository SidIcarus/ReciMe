package com.recime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.recime.Recipe;

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
