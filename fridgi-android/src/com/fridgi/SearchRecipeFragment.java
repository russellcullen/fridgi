package com.fridgi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.fridgi.adapters.RecipeAdapter;
import com.fridgi.models.Ingredient;
import com.fridgi.models.Recipe;
import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;
import com.fridgi.tasks.SearchFridgeRecipesTask;
import com.fridgi.util.Globals;
import com.fridgi.util.Util;

import java.util.List;

public class SearchRecipeFragment extends Fragment implements FridgeCallback {
    
    public static int REQUEST_CODE_RECIPE = 500;

    private ListView mList;
    private EditText mSearchBox;
    private String mQuery;
    private MenuItem mRefresh;

    public SearchRecipeFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mSearchBox = (EditText) getActivity().findViewById(R.id.search);
        mSearchBox.setOnEditorActionListener(new OnEditorActionListener() {
            
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    if (v.getText().toString().length() > 0) {
                        // Perform action on key press
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(),
                                0);
                        startSearch(v.getText().toString().toLowerCase());
                    }
                }
                return true;
            }
        });
        
        List<Recipe> recipes = Globals.getInstance().getFridge().getRecentRecipes();
        if (recipes != null) {
            RecipeAdapter adapter = new RecipeAdapter(getActivity(), setCanCook(recipes));
            mList.setAdapter(adapter);
        }
        
        mList.setOnItemClickListener(mOnRecipeClicked);
        
        FridgeTask task = new FridgeTask(Globals.getInstance().getFridge().getName(), this);
        task.execute();
    }
    
    public void onPostExecute() {
        List<Recipe> recipes = Globals.getInstance().getFridge().getRecentRecipes();
        RecipeAdapter adapter = new RecipeAdapter(getActivity(), setCanCook(recipes));
        mList.setAdapter(adapter);
        if (mRefresh != null) {
            mRefresh.setActionView(null);
        }
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                refresh();
                break;
            case Activity.RESULT_CANCELED:
                refreshQuery();
                break;
        }
    }
    
    private void refresh() {
        if (mSearchBox != null) {
            mSearchBox.setText("");
        }
        List<Recipe> recipes = Globals.getInstance().getFridge().getRecentRecipes();
        if (recipes != null) {
            RecipeAdapter adapter = new RecipeAdapter(getActivity(), setCanCook(recipes));
            mList.setAdapter(adapter);
        }
        FridgeTask task = new FridgeTask(Globals.getInstance().getFridge().getName(), this);
        task.execute();
    }
    
    private List<Recipe> setCanCook(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            recipe.setCanCook(true);
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (!Util.hasIngredient(ingredient)) {
                    recipe.setCanCook(false);
                    break;
                }
            }
        }
        return recipes;
    }
    
    private void refreshQuery() {
        startSearch(mQuery);
    }
    
    private void startSearch(String query) {
        if (query == null) return;
        mQuery = query;
        SearchFridgeRecipesTask task = new SearchFridgeRecipesTask(query, Globals.getInstance().getFridge().getName(), getActivity(), mList);
        task.execute();
        if (mRefresh != null) {
            mRefresh.setActionView(null);
        }
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh, menu);
        mRefresh = menu.getItem(0);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                item.setActionView(R.layout.refresh_menuitem);
                if (mSearchBox.getText().length() == 0) {
                    refresh();
                } else {
                    refreshQuery();
                }
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_search_detail, container, false);
        mList = (ListView) rootView.findViewById(R.id.recipes);
        return rootView;
    }
    
    OnItemClickListener mOnRecipeClicked = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(getActivity(), RecipeActivity.class);
            i.putExtra(RecipeActivity.INTENT_EXTRA_RECIPE, (Recipe)mList.getItemAtPosition(position));
            startActivityForResult(i, SearchRecipeFragment.REQUEST_CODE_RECIPE);
        }
    };
    
}
