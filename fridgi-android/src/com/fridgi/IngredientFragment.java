package com.fridgi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;
import com.fridgi.util.Globals;

public class IngredientFragment extends Fragment implements FridgeCallback {

    public static final int TYPE_GROCERY = 1;
    public static final int TYPE_FRIDGE = 2;
    
    int mType;
    ListView mList;
    IngredientAdapter mAdapter;

    public IngredientFragment(int type) {
        mType = type;
    }
    
    public IngredientFragment() {
        this(TYPE_FRIDGE);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mType == TYPE_GROCERY) {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getGroceryList());
            mList.setAdapter(mAdapter);
            FridgeTask task = new FridgeTask(this);
            task.execute();
        } else if (mType == TYPE_FRIDGE) {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getIngredients());
            mList.setAdapter(mAdapter);
            FridgeTask task = new FridgeTask(this);
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
        View rootView = inflater.inflate(R.layout.fragment_ingredient_detail, container, false);
        mList = (ListView) rootView.findViewById(R.id.ingredient_detail);
        return rootView;
    }
}
