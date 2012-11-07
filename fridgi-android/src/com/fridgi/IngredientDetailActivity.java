package com.fridgi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class IngredientDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(IngredientDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(IngredientDetailFragment.ARG_ITEM_ID));
            Fragment fragment;
            if (getIntent().getStringExtra(IngredientDetailFragment.ARG_ITEM_ID).equals("2")) {
                fragment = new SearchRecipeFragment();
            } else {
                fragment = new IngredientDetailFragment();
            }
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ingredient_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, IngredientListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
