package com.fridgi.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fridgi.R;
import com.fridgi.RecipeActivity;
import com.fridgi.models.Recipe;
import com.fridgi.util.Util;

import java.util.List;


public class RecipeAdapter extends BaseAdapter {
    
    private List<Recipe> mRecipes;
    private LayoutInflater mInflater;
    private Context mContext;
    
    public RecipeAdapter(Context context, List<Recipe> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mRecipes = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            ((TextView) convertView.findViewById(android.R.id.text1)).setTypeface(null, Typeface.BOLD);
        }
        
        final Recipe recipe = mRecipes.get(position);
        convertView.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, RecipeActivity.class);
                i.putExtra(RecipeActivity.INTENT_EXTRA_RECIPE, recipe);
                mContext.startActivity(i);
            }
        });
        
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(Util.titleCase(recipe.getName()));
        int green = mContext.getResources().getColor(R.color.can_cook);
        int red = mContext.getResources().getColor(R.color.cannot_cook);
        ((TextView) convertView.findViewById(android.R.id.text1)).setTextColor(recipe.isCanCook() ? green : red);
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
