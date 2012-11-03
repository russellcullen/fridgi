package com.fridgi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fridgi.models.BaseIngredient;

import java.util.ArrayList;


public class BaseIngredientAdapter extends BaseAdapter {
    
    private ArrayList<BaseIngredient> mIngredients;
    private LayoutInflater mInflater;
    
    public BaseIngredientAdapter(Context context, ArrayList<BaseIngredient> ingredients) {
        mInflater = LayoutInflater.from(context);
        mIngredients = ingredients;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_2, null);
        }
        
        BaseIngredient ingredient = mIngredients.get(position);
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(ingredient.getName());
        ((TextView) convertView.findViewById(android.R.id.text2)).setText(ingredient.getQuantity() + " " + ingredient.getUnit());
        
        return convertView;
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
