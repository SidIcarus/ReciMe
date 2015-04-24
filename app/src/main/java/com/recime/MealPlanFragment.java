package com.recime;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.recime.models.RecipeStep;
import com.recime.models.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MealPlanFragment extends Fragment implements TextToSpeech.OnInitListener {
    private ListView listView;
    private TextToSpeech textToSpeech;
    private TextToSpeech.OnInitListener onInit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getActivity();
        onInit = this;

        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.meal_plan_view, container, false);

        // Get ListView object from xml
        listView = (ListView) V.findViewById(R.id.mealPlanList);

        listView.setAdapter(new RecipeListAdapter(context, R.layout.list_view_row, MealPlanSingleton.getInstance().getMealPlanRecipes()));

        Button startMeal = (Button)V.findViewById(R.id.startMealPlan);
        startMeal.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                textToSpeech = new TextToSpeech(getActivity(), onInit);

            }
        });



        return V;
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if(status == TextToSpeech.SUCCESS){
            int result=textToSpeech.setLanguage(Locale.US);
            if(result==TextToSpeech.LANG_MISSING_DATA ||
                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("error", "This Language is not supported");
            }else{
                convertTextToSpeech();
            }
        }
        else
            Log.e("error", "Initilization Failed!");
    }

    /**
     * Releases the resources used by the TextToSpeech engine. It is good
     * practice for instance to call this method in the onDestroy() method of an
     * Activity so the TextToSpeech engine can be cleanly stopped.
     *
     * @see android.app.Activity#onDestroy()
     */
    @Override
    public void onDestroy() {
        textToSpeech.shutdown();
    }

    /**
     * Speaks the string using the specified queuing strategy and speech
     * parameters.
     */
    private void convertTextToSpeech() {
        String recipeName = "Cup-cakes; ";
        List<String> steps = Arrays.asList(
            "Gather Ingredients",
            "Preheat oven to 350",
            "Mix Dry Ingredients",
            "Mix Wet Ingredients in separate bowls",
            "Combine bowls",
            "Spray pans",
            "Pour mixture into pans",
            "Bake cupcakes for twenty minutes");

        for (int i = 0; i < steps.size(); i++ ) {
            textToSpeech.speak(recipeName + " " + steps.get(i), TextToSpeech.QUEUE_ADD, null);
        }

    }

    public class RecipeListAdapter extends ArrayAdapter<Recipe> {

        public RecipeListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public RecipeListAdapter(Context context, int resource, List<Recipe> items) {
            super(context, resource, items);
            System.out.println("size " + items.size());
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
                tt.setText(recipe.getName());
            }

            return v;

        }
    }


}