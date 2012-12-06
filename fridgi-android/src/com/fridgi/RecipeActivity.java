package com.fridgi;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.fridgi.util.Util;

public class RecipeActivity extends FragmentActivity implements 
    SuggestRecipeHandler {
    
    public static String INTENT_EXTRA_RECIPE = "INTENT_EXTRA_RECIPE";
    
    private MergeAdapter mAdapter;
    private Recipe mRecipe;
    private RecipeIngredient mSelected;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        
        ActionBar ab = getActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        
        mRecipe = getIntent().getParcelableExtra(INTENT_EXTRA_RECIPE);
        
        mAdapter = new MergeAdapter();
        
        LayoutInflater inflater = getLayoutInflater();
        View titleParent = inflater.inflate(R.layout.title, null);
        TextView title = (TextView) titleParent.findViewById(R.id.title);
        title.setText(Util.titleCase(mRecipe.getName()));
        
        setTitle(Util.titleCase(mRecipe.getName()));
        
        mAdapter.addView(titleParent);
        
        RecipeIngredientAdapter ingredients = new RecipeIngredientAdapter(this, mRecipe.getIngredients());
        mAdapter.addAdapter(ingredients);
        
        StringBuilder sb = new StringBuilder();
        String[] instructions = mRecipe.getInstructions();
        for (int i = 0; i < instructions.length; i++) {
            sb.append(instructions[i] + "\n\n");
        }
        View textParent = inflater.inflate(R.layout.text, null);
        TextView text = (TextView) textParent.findViewById(R.id.text);
        text.setText(sb.toString());
        mAdapter.addView(textParent);
        
        Button btn = (Button) inflater.inflate(R.layout.button, null);
        if (mRecipe.isCanCook()) {
            btn.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    UseRecipeTask task = new UseRecipeTask(Globals.getInstance().getFridge().getName(), mRecipe.getId().getId());
                    task.execute();
                    Util.refreshFridge();
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            });
            btn.setText("USE THIS RECIPE");
        } else {
            btn.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    mDialog = new ProgressDialog(RecipeActivity.this);
                    mDialog.setMessage("Loading Recipe...");
                    mDialog.show();
                    SuggestRecipeTask task = new SuggestRecipeTask(Globals.getInstance().getFridge().getName(), mRecipe.getId().getId(), RecipeActivity.this);
                    task.execute();
                }
            });
            btn.setText("SUGGEST");
           
        }
        mAdapter.addView(btn);
        ListView list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(mOnIngredientClickedListener);
        list.setAdapter(mAdapter);
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
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
            Util.refreshFridge();
        }
    };
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed
                // in the Action Bar.
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
