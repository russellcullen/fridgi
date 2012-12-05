package com.fridgi;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;
import com.fridgi.util.Globals;

public class IngredientFragment extends ListFragment implements FridgeCallback {
    
    public static final String TYPE = "TYPE";

    public static final int TYPE_GROCERY = 1;
    public static final int TYPE_FRIDGE = 2;
    
    int mType;
    IngredientAdapter mAdapter;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mType = getArguments().getInt(TYPE);
        if (mType == TYPE_GROCERY) {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getGroceryList());
            getListView().setAdapter(mAdapter);
            FridgeTask task = new FridgeTask(Globals.getInstance().getFridge().getName(), this);
            task.execute();
        } else if (mType == TYPE_FRIDGE) {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getIngredients());
            getListView().setAdapter(mAdapter);
            FridgeTask task = new FridgeTask(Globals.getInstance().getFridge().getName(), this);
            task.execute();
        }
    }
    
    public void onPostExecute() {
        if (mType == TYPE_GROCERY) {
            mAdapter.setIngredients(Globals.getInstance().getFridge().getGroceryList());
        } else {
            mAdapter.setIngredients(Globals.getInstance().getFridge().getIngredients());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredient_detail, container, false);
    }
}
