package com.recime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import com.recime.Recipe;

public class RecipeListFragment extends Fragment {
    ListView listView ;

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

        // Define a new RecipeListAdapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third - the Array of data

        listView.setAdapter(new RecipeListAdapter(context, R.layout.list_view_row, recipes));

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
                TextView tt = (TextView) v.findViewById(R.id.textView);
                tt.setText(recipe.name);
            }

            return v;

        }
    }

}