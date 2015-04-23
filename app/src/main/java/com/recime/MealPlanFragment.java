package com.recime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MealPlanFragment extends Fragment {
    private TextView displayTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.meal_plan_view, container, false);

        TextView displayTextView = (TextView)V.findViewById(R.id.shoppingTitle);
        displayTextView.setText("OMG IT WORKED");

        return V;
    }
}