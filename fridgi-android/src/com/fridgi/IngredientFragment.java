package com.fridgi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fridgi.dummy.DummyContent;
import com.fridgi.tasks.FridgeIngredientsTask;
import com.fridgi.tasks.IngredientsTask;
import com.fridgi.tasks.RecipesTask;

public class IngredientFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    ListView mList;

    public IngredientFragment() {
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
            IngredientsTask task = new IngredientsTask(getActivity(), mList);
            task.execute();
        } else if (getArguments().getString(ARG_ITEM_ID).equals("2")) {
            RecipesTask task = new RecipesTask(getActivity(), mList);
            task.execute();
        } else {
            FridgeIngredientsTask task = new FridgeIngredientsTask(getActivity(), mList);
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
}
