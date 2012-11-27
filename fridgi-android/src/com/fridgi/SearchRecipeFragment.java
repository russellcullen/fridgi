package com.fridgi;

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

import com.fridgi.tasks.SearchFridgeRecipesTask;
import com.fridgi.util.Globals;

public class SearchRecipeFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private ListView mList;
    private EditText mSearchBox;
    private String mQuery;

    public SearchRecipeFragment() {
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
    
    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
    
    private void refresh() {
        startSearch(mQuery);
    }
    
    private void startSearch(String query) {
        if (query == null) return;
        mQuery = query;
        SearchFridgeRecipesTask task = new SearchFridgeRecipesTask(query, Globals.getInstance().getFridge().getName(), getActivity(), mList);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_search_detail, container, false);
        mList = (ListView) rootView.findViewById(R.id.recipes);
        return rootView;
    }
    
}
