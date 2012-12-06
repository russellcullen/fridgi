package com.fridgi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.models.RecipeIngredient;
import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;
import com.fridgi.tasks.RemoveFromGroceryTask;
import com.fridgi.util.Globals;

public class IngredientFragment extends ListFragment implements FridgeCallback {
    
    public static final String TYPE = "TYPE";

    public static final int TYPE_GROCERY = 1;
    public static final int TYPE_FRIDGE = 2;
    
    int mType;
    IngredientAdapter mAdapter;
    private RecipeIngredient mSelected;
    private MenuItem mRefresh;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mType = getArguments().getInt(TYPE);
        if (mType == TYPE_GROCERY) {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getGroceryList());
            getListView().setAdapter(mAdapter);
            getListView().setOnItemClickListener(mOnIngredientClickedListener);
        } else if (mType == TYPE_FRIDGE) {
            mAdapter = new IngredientAdapter(getActivity(), Globals.getInstance().getFridge().getIngredients(), false);
            getListView().setAdapter(mAdapter);
        }
        FridgeTask task = new FridgeTask(Globals.getInstance().getFridge().getName(), this);
        task.execute();
    }
    
    public void onPostExecute() {
        if (mType == TYPE_GROCERY) {
            mAdapter.setIngredients(Globals.getInstance().getFridge().getGroceryList());
        } else {
            mAdapter.setIngredients(Globals.getInstance().getFridge().getIngredients());
        }
        if (mRefresh != null) {
            mRefresh.setActionView(null);
        }
    }
    
    public void refresh() {
        FridgeTask fridge = new FridgeTask(Globals.getInstance().getFridge().getName(), IngredientFragment.this);
        fridge.execute();
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh, menu);
        mRefresh = menu.getItem(0);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                item.setActionView(R.layout.refresh_menuitem);
                refresh();
        }
        return true;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        onPostExecute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredient_detail, container, false);
    }
    
    OnItemClickListener mOnIngredientClickedListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object obj = mAdapter.getItem(position);
            if (obj instanceof RecipeIngredient) {
                mSelected = (RecipeIngredient) obj;
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setMessage("Are you sure you want to remove " + mSelected.getName() + " from your list?")
                    .setPositiveButton("OK", mOnRemoveListener)
                    .create();
                dialog.show();
            }
        }
    };
    
    DialogInterface.OnClickListener mOnRemoveListener = new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
            RemoveFromGroceryTask task = new RemoveFromGroceryTask(Globals.getInstance().getFridge().getName(), 
                    mSelected.getIngredient().getId());
            task.execute();
            mAdapter.removeIngredient(mSelected);
            refresh();
        }
    };
}
