package com.fridgi.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fridgi.R;
import com.fridgi.models.RecipeIngredient;
import com.fridgi.util.Util;

import java.util.List;


public class RecipeIngredientAdapter extends BaseAdapter {
    
    private List<RecipeIngredient> mIngredients;
    private LayoutInflater mInflater;
    
    public RecipeIngredientAdapter(Context context, List<RecipeIngredient> ingredients) {
        mInflater = LayoutInflater.from(context);
        mIngredients = ingredients;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.small_list_item, null);
        }
        
        RecipeIngredient ingredient = mIngredients.get(position);
        TextView name = ((TextView) convertView.findViewById(R.id.name));
        name.setText(ingredient.getName());
        if (Util.hasIngredient(ingredient)) {
            name.setTextColor(Color.GREEN);
        } else {
            name.setTextColor(Color.RED);
        }
        ((TextView) convertView.findViewById(R.id.quantity)).setText(Util.formatIngredientQuantity(ingredient, ingredient.getUnit()));
        
        return convertView;
    }
    
    public void setIngredients(List<RecipeIngredient> ingredients) {
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
