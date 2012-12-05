package com.fridgi;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity implements
    ActionBar.TabListener {

    
    FridgiPagerAdapter mFridgiPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        mFridgiPagerAdapter = new FridgiPagerAdapter(
                getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mFridgiPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);

        mViewPager
            .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
              @Override
              public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
              }
            });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mFridgiPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab()
              .setText(mFridgiPagerAdapter.getPageTitle(i))
              .setTabListener(this));
        }
    }
    
    public class FridgiPagerAdapter extends FragmentPagerAdapter {
        
        public FridgiPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            Fragment frag = null;
            Bundle args = new Bundle();
            switch (pos) {
                case 0:
                    args.putInt(IngredientFragment.TYPE, IngredientFragment.TYPE_FRIDGE);
                    frag = new IngredientFragment();
                    break;
                case 1:
                    frag = new SearchRecipeFragment();
                    break;
                case 2:
                    args.putInt(IngredientFragment.TYPE, IngredientFragment.TYPE_GROCERY);
                    frag = new IngredientFragment();
                    break;
            }
            frag.setArguments(args);
            return frag;
        }

        @Override
        public int getCount() {
            return 3;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Ingredients";
                case 1:
                    return "Recipes";
                case 2:
                    return "Grocery";
            }
            return "NO TITLE";
        }
    
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
}
