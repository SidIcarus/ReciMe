package com.recime;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.recime.Recipe;
import com.recime.RecipeStep;

import java.util.List;

public class RecipeFragment extends Fragment {
    private Recipe recipeObject;
    ListView listView ;

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
        titleTextView.setText(recipeObject.name);

        List<RecipeStep> recipeSteps = recipeObject.getRecipeSteps();

        // Get ListView object from xml
        listView = (ListView) V.findViewById(R.id.stepsList);

        listView.setAdapter(new RecipeListAdapter(context, R.layout.list_view_row, recipeSteps));

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
                tt.setText(recipeStep.action);

                TextView numOrder = (TextView) v.findViewById((R.id.numOrder));
                numOrder.setText(recipeStep.stepNum.toString());
            }

            return v;

        }
    }


}