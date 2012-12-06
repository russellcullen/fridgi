package com.fridgi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fridgi.R;
import com.fridgi.models.Ingredient;
import com.fridgi.util.Util;

import java.util.List;


public class IngredientAdapter extends BaseAdapter {
    
    private List<? extends Ingredient> mIngredients;
    private LayoutInflater mInflater;
    private Context mContext;
    private boolean mHasSelector;
    
    public IngredientAdapter(Context context, List<? extends Ingredient> ingredients) {
        this(context, ingredients, true);
    }
    
    public IngredientAdapter(Context context, List<? extends Ingredient> ingredients, boolean hasSelector) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mIngredients = ingredients;
        mHasSelector = hasSelector;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            if (!mHasSelector) {
                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.fg));
            }
        }
        
        Ingredient ingredient = mIngredients.get(position);
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(Util.titleCase(ingredient.getName()));
        ((TextView) convertView.findViewById(android.R.id.text2)).setText("" + ingredient.getQuantity() + " " + ingredient.getUnit());
        
        return convertView;
    }
    
    public void setHasSlector(boolean hasSelector) {
        mHasSelector = hasSelector;
    }
    
    public void setIngredients(List<? extends Ingredient> ingredients) {
        mIngredients = ingredients;
        notifyDataSetChanged();
    }
    
    public void removeIngredient(Ingredient ingredient) {
        mIngredients.remove(ingredient);
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
