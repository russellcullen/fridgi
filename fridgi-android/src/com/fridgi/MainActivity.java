package com.fridgi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity
        implements MasterListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
        
        if (findViewById(R.id.ingredient_detail_container) != null) {
            mTwoPane = true;
            ((MasterListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.ingredient_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(IngredientFragment.ARG_ITEM_ID, id);
            Fragment fragment;
            if (id.equals("2")) {
                fragment = new SearchRecipeFragment();
            } else {
                fragment = new IngredientFragment();
            }
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ingredient_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, DetailActivity.class);
            detailIntent.putExtra(IngredientFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
