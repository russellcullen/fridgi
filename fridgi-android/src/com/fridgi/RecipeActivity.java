package com.fridgi;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.fridgi.models.Recipe;
import com.fridgi.models.RecipeIngredient;

public class RecipeActivity extends FragmentActivity {
    
    public static String INTENT_EXTRA_RECIPE = "INTENT_EXTRA_RECIPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        
        Recipe recipe = getIntent().getParcelableExtra(INTENT_EXTRA_RECIPE);
        
        ((TextView) findViewById(R.id.title)).setText(recipe.getName());
        
        StringBuilder sb = new StringBuilder();
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            sb.append(ingredient.getQuantity() + " " + ingredient.getUnit() + " " + ingredient.getName() + "\n");
        }
        ((TextView) findViewById(R.id.ingredients)).setText(sb.toString());
        
        sb = new StringBuilder();
        String[] instructions = recipe.getInstructions();
        for (int i = 0; i < instructions.length; i++) {
            sb.append(instructions[i] + "\n\n");
        }
        ((TextView) findViewById(R.id.instructions)).setText(sb.toString());
    }

}
