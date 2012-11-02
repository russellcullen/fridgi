package com.fridgi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fridgi.api.Api;
import com.fridgi.dummy.DummyContent;
import com.fridgi.models.Ingredient;

import java.util.ArrayList;

public class IngredientDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    TextView mText;

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
        IngredientsTask task = new IngredientsTask();
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredient_detail, container, false);
        if (mItem != null) {
            mText = ((TextView) rootView.findViewById(R.id.ingredient_detail));
            mText.setText("Loading...");
        }
        return rootView;
    }
    
    public class IngredientsTask extends AsyncTask<Void, Void, ArrayList<Ingredient>> {

        @Override
        protected ArrayList<Ingredient> doInBackground(Void... params) {
            return Api.getIngredients();
        }
        
        @Override
        protected void onPostExecute(ArrayList<Ingredient> result) {
            if (mText != null) {
                StringBuilder sb = new StringBuilder();
                for (Ingredient i : result) {
                    sb.append(i.getQuantity());
                    sb.append(" " + i.getName());
                    sb.append("\n");
                }
                mText.setText(sb.toString());
            }
        }

        
    }

}
