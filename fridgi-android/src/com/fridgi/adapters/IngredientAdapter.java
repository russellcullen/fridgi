package com.fridgi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fridgi.R;
import com.fridgi.models.Ingredient;

import java.util.List;


public class IngredientAdapter extends BaseAdapter {
    
    public static int SMALL_LIST = 0;
    public static int LARGE_LIST = 1;
    
    private List<? extends Ingredient> mIngredients;
    private LayoutInflater mInflater;
    private int mSize;
    
    public IngredientAdapter(Context context, List<? extends Ingredient> ingredients, int size) {
        mInflater = LayoutInflater.from(context);
        mIngredients = ingredients;
        mSize = size;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (mSize == SMALL_LIST) {
                convertView = mInflater.inflate(R.layout.small_list_item, null);
            } else {
                convertView = mInflater.inflate(android.R.layout.simple_list_item_2, null);
            }
        }
        
        Ingredient ingredient = mIngredients.get(position);
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(ingredient.getName());
        ((TextView) convertView.findViewById(android.R.id.text2)).setText("" + ingredient.getQuantity());
        
        return convertView;
    }
    
    public void setIngredients(List<? extends Ingredient> ingredients) {
        mIngredients = ingredients;
    }
    
    @Override
    public int getCount() {
        return mIngredients.size();
    }

    @Override
    public Object getItem(int position) {
        return mIngredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
