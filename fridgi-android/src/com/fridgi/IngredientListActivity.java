package com.fridgi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class IngredientListActivity extends FragmentActivity
        implements IngredientListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        if (findViewById(R.id.ingredient_detail_container) != null) {
            mTwoPane = true;
            ((IngredientListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.ingredient_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(IngredientDetailFragment.ARG_ITEM_ID, id);
            IngredientDetailFragment fragment = new IngredientDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ingredient_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, IngredientDetailActivity.class);
            detailIntent.putExtra(IngredientDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
