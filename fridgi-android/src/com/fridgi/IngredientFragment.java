package com.fridgi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.dummy.DummyContent;
import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;
import com.fridgi.tasks.IngredientsTask;
import com.fridgi.util.Globals;

public class IngredientFragment extends Fragment implements FridgeCallback {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    ListView mList;
    IngredientAdapter mAdapter;

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
        } else if (getArguments().getString(ARG_ITEM_ID).equals("4")) {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getGroceryList());
            mList.setAdapter(mAdapter);
            FridgeTask task = new FridgeTask(this);
            task.execute();
        } else {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getIngredients());
            mList.setAdapter(mAdapter);
            FridgeTask task = new FridgeTask(this);
            task.execute();
        }
    }
    
    public void onPostExecute() {
        if (getArguments().getString(ARG_ITEM_ID).equals("4")) {
            mAdapter.setIngredients(Globals.getInstance().getFridge().getGroceryList());
        } else {
            mAdapter.setIngredients(Globals.getInstance().getFridge().getIngredients());
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
