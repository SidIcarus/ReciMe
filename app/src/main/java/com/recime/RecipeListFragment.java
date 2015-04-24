package com.recime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import com.recime.models.Recipe;

public class RecipeListFragment extends Fragment {
    ListView listView ;

    public static List<Recipe> allRecipes;
    public static List<Recipe> shownRecipes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getActivity();

        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.recipe_list_view, container, false);

        // Get ListView object from xml
        listView = (ListView) V.findViewById(R.id.listView);

        // Defined Array values to show in ListView
        List<Recipe> recipes = Recipe.listAll(Recipe.class);
        allRecipes = Recipe.listAll(Recipe.class);
        shownRecipes = recipes;
        // Define a new RecipeListAdapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third - the Array of data

        listView.setAdapter(new RecipeListAdapter(context, R.layout.list_view_row, shownRecipes));

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                Recipe recipe = (Recipe) listView.getItemAtPosition(position);

                Bundle mBundle = new Bundle();
                mBundle.putString("recipeId", recipe.getId().toString());

                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("recipeId", recipe.getId().toString());
                startActivity(intent);
            }
        });


        EditText whythehellwhyy = (EditText)V.findViewById(R.id.searchField2);

        whythehellwhyy.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                //Fragment f = getFragmentManager().findFragmentById(R.id.listView);

                synchronized (RecipeListFragment.shownRecipes) {
                    String searchString = s.toString();
                    if (searchString == null)
                        searchString = "";
                    searchString = searchString.toLowerCase();

                    RecipeListFragment.shownRecipes.clear();

                    for (Recipe r : RecipeListFragment.allRecipes) {
                        String name = r.getName().toLowerCase();
                        if (name.contains(searchString) || searchString.contains((name))) {
                            RecipeListFragment.shownRecipes.add(r);
                        }
                    }
                    listView.invalidateViews();
                }
                //RecipeListFragment.IM_USING_A_STATIC_BECAUSE_I_CAN.searchString = s.toString();
            }
        });
        return V;
    }

    public class RecipeListAdapter extends ArrayAdapter<Recipe> {

        public RecipeListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public RecipeListAdapter(Context context, int resource, List<Recipe> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;



            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.list_view_row, null);
            }

            Recipe recipe = getItem(position);

            if (recipe != null) {
                String name = recipe.getName();

                //if (name.contains(searchString) || searchString.contains((name))) {
                    TextView tt = (TextView) v.findViewById(R.id.textView);

                    tt.setText(name);
                //}
            }

            return v;

        }
    }

}