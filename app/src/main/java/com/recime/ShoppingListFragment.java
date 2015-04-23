package com.recime;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.recime.models.Ingredient;
import com.recime.models.Recipe;
import com.recime.models.RecipeStep;

import java.util.List;

public class ShoppingListFragment extends Fragment {
    private ListView listView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getActivity();

        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.shopping_list_view, container, false);

        List<Ingredient> ingredientList = ShoppingListSingleton.getInstance().getShoppingList();

        // Get ListView object from xml
        listView = (ListView) V.findViewById(R.id.shoppingListView);
        listView.setAdapter(new ShoppingListAdapter(context, R.layout.list_view_row, ingredientList));



        return V;
    }

    public class ShoppingListAdapter extends ArrayAdapter<Ingredient> {

        public ShoppingListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ShoppingListAdapter(Context context, int resource, List<Ingredient> items) {
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

            Ingredient ingredient = getItem(position);

            if (ingredient != null) {
                TextView tt = (TextView) v.findViewById(R.id.stepRow);
                tt.setText(ingredient.getName());
            }

            return v;

        }
    }
}
