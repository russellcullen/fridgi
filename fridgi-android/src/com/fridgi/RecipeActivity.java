package com.fridgi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;
import com.fridgi.adapters.RecipeIngredientAdapter;
import com.fridgi.models.Recipe;
import com.fridgi.models.RecipeIngredient;
import com.fridgi.tasks.AddToGroceryTask;
import com.fridgi.tasks.SuggestRecipeTask;
import com.fridgi.tasks.SuggestRecipeTask.SuggestRecipeHandler;
import com.fridgi.tasks.UseRecipeTask;
import com.fridgi.util.Globals;

public class RecipeActivity extends FragmentActivity implements SuggestRecipeHandler {
    
    public static String INTENT_EXTRA_RECIPE = "INTENT_EXTRA_RECIPE";
    
    private MergeAdapter mAdapter;
    private Recipe mRecipe;
    private RecipeIngredient mSelected;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        
        mRecipe = getIntent().getParcelableExtra(INTENT_EXTRA_RECIPE);
        
        mAdapter = new MergeAdapter();
        
        LayoutInflater inflater = getLayoutInflater();
        TextView title = (TextView) inflater.inflate(R.layout.title, null);
        title.setText(mRecipe.getName());
        
        mAdapter.addView(title);
        
        RecipeIngredientAdapter ingredients = new RecipeIngredientAdapter(this, mRecipe.getIngredients());
        mAdapter.addAdapter(ingredients);
        
        StringBuilder sb = new StringBuilder();
        String[] instructions = mRecipe.getInstructions();
        for (int i = 0; i < instructions.length; i++) {
            sb.append(instructions[i] + "\n\n");
        }
        TextView instructionsView = (TextView) inflater.inflate(R.layout.text, null);
        instructionsView.setText(sb.toString());
        mAdapter.addView(instructionsView);
        
        Button useRecipeBtn = new Button(this);
        useRecipeBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                UseRecipeTask task = new UseRecipeTask(Globals.getInstance().getFridge().getName(), mRecipe.getId().getId());
                task.execute();
                finish();
            }
        });
        useRecipeBtn.setText("USE THIS RECIPE");
        mAdapter.addView(useRecipeBtn);
        
        if (!mRecipe.isCanCook()) {
            Button suggestBtn = new Button(this);
            suggestBtn.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    mDialog = new ProgressDialog(RecipeActivity.this);
                    mDialog.setMessage("Loading Recipe...");
                    mDialog.show();
                    SuggestRecipeTask task = new SuggestRecipeTask(Globals.getInstance().getFridge().getName(), mRecipe.getId().getId(), RecipeActivity.this);
                    task.execute();
                }
            });
            suggestBtn.setText("SUGGEST");
            mAdapter.addView(suggestBtn);
        }
        
        ListView list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(mOnIngredientClickedListener);
        list.setAdapter(mAdapter);
    }
    
    public void handleRecipe(Recipe recipe) {
        mDialog.dismiss();
        Intent i = new Intent(this, RecipeActivity.class);
        i.putExtra(RecipeActivity.INTENT_EXTRA_RECIPE, recipe);
        startActivity(i);
    }
    
    OnItemClickListener mOnIngredientClickedListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object obj = mAdapter.getItem(position);
            if (obj instanceof RecipeIngredient) {
                mSelected = (RecipeIngredient) obj;
                AlertDialog dialog = new AlertDialog.Builder(RecipeActivity.this)
                    .setMessage("Are you sure you want to add " + mSelected.getName() + " to your list?")
                    .setPositiveButton("OK", mOnAddListener)
                    .create();
                dialog.show();
            }
        }
    };
    
    DialogInterface.OnClickListener mOnAddListener = new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
            AddToGroceryTask task = new AddToGroceryTask(Globals.getInstance().getFridge().getName(), 
                    mSelected.getIngredient().getId(), 
                    mSelected.getQuantity());
            task.execute();
        }
    };

}
