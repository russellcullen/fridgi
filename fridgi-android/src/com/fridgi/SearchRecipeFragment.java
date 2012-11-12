package com.fridgi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.fridgi.adapters.RecipeAdapter;
import com.fridgi.api.Api;
import com.fridgi.dummy.DummyContent;
import com.fridgi.models.Recipe;

import java.util.ArrayList;

public class SearchRecipeFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    ListView mList;
    EditText mSearchBox;

    public SearchRecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSearchBox = (EditText) getActivity().findViewById(R.id.search);
        mSearchBox.setOnEditorActionListener(new OnEditorActionListener() {
            
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                startSearch(v.getText().toString());
                return true;
            }
        });
    }
    
    private void startSearch(String query) {
        SearchTask task = new SearchTask(query);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_search_detail, container, false);
        mList = (ListView) rootView.findViewById(R.id.recipes);
        return rootView;
    }
    
    public class SearchTask extends AsyncTask<Void, Void, ArrayList<Recipe>> {
        
        private String mQuery;
        
        public SearchTask(String query) {
            mQuery = query;
        }

        @Override
        protected ArrayList<Recipe> doInBackground(Void... params) {
            return Api.searchRecipes(mQuery);
        }
        
        @Override
        protected void onPostExecute(ArrayList<Recipe> result) {
            RecipeAdapter adapter = new RecipeAdapter(getActivity(), result);
            mList.setAdapter(adapter);
        }      
    }


}
