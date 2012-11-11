package com.fridgi.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fridgi.models.Recipe;

import java.util.ArrayList;


public class RecipeAdapter extends BaseAdapter {
    
    private ArrayList<Recipe> mRecipes;
    private LayoutInflater mInflater;
    
    public RecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        mInflater = LayoutInflater.from(context);
        mRecipes = recipes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_2, null);
            ((TextView) convertView.findViewById(android.R.id.text1)).setTypeface(null, Typeface.BOLD);
        }
        
        Recipe recipe = mRecipes.get(position);
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(recipe.getName());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipe.getInstructions().length; i++) {
            sb.append("\t" + recipe.getInstructions()[i] + "\n\n");
        }
        TextView desc = (TextView) convertView.findViewById(android.R.id.text2);
        desc.setText(sb.toString());
        desc.setMaxLines(3);
        desc.setPadding(0, 0, 0, 10);
        desc.setEllipsize(TextUtils.TruncateAt.END);
        
        return convertView;
    }
    
    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
