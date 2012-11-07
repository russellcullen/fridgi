package com.fridgi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.adapters.RecipeAdapter;
import com.fridgi.api.Api;
import com.fridgi.dummy.DummyContent;
import com.fridgi.models.BaseIngredient;
import com.fridgi.models.Fridge;
import com.fridgi.models.Recipe;

import java.util.ArrayList;

public class IngredientDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    ListView mList;

    public IngredientDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }
    
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments().getString(ARG_ITEM_ID).equals("1")) {
            IngredientsTask task = new IngredientsTask();
            task.execute();
        } else if (getArguments().getString(ARG_ITEM_ID).equals("2")) {
            RecipesTask task = new RecipesTask();
            task.execute();
        } else {
            FridgeTask task = new FridgeTask();
            task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredient_detail, container, false);
        mList = (ListView) rootView.findViewById(R.id.ingredient_detail);
        return rootView;
    }
    
    public class IngredientsTask extends AsyncTask<Void, Void, ArrayList<BaseIngredient>> {

        @Override
        protected ArrayList<BaseIngredient> doInBackground(Void... params) {
            return Api.getIngredients();
        }
        
        @Override
        protected void onPostExecute(ArrayList<BaseIngredient> result) {
            IngredientAdapter adapter = new IngredientAdapter(getActivity(), result);
            mList.setAdapter(adapter);
        }      
    }
    
    public class RecipesTask extends AsyncTask<Void, Void, ArrayList<Recipe>> {

        @Override
        protected ArrayList<Recipe> doInBackground(Void... params) {
            return Api.getRecipes();
        }
        
        @Override
        protected void onPostExecute(ArrayList<Recipe> result) {
            RecipeAdapter adapter = new RecipeAdapter(getActivity(), result);
            mList.setAdapter(adapter);
        }      
    }
    
    public class FridgeTask extends AsyncTask<Void, Void, Fridge> {

        @Override
        protected Fridge doInBackground(Void... params) {
            return Api.getFridge("fridgi");
        }
        
        @Override
        protected void onPostExecute(Fridge result) {
            IngredientAdapter adapter = new IngredientAdapter(getActivity(), result.getIngredients());
            mList.setAdapter(adapter);
        }      
    }

}
