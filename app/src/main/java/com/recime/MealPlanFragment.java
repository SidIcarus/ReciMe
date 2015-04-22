package com.recime;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.Arrays;
import java.util.List;

public class MealPlanFragment extends Fragment {
    private TextView displayTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.meal_plan_view, container, false);

        TextView displayTextView = (TextView)V.findViewById(R.id.shoppingTitle);
        displayTextView.setText("OMG IT WORKED");





        Recipe recipe = new Recipe("testRecipe");
        recipe.save();

        System.out.println("hello there everyone");
        System.out.println(recipe.getId());
        System.out.println(recipe.name);

        return V;
    }


}