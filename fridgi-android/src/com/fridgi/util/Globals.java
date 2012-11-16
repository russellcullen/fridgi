package com.fridgi.util;

import com.fridgi.models.Fridge;

public class Globals {
    
    private static Globals mInstance;
    
    private Fridge mFridge;
    
    private Globals() {}
    
    public static Globals getInstance() {
        if (mInstance == null) {
            mInstance = new Globals();
        }
        return mInstance;
    }
    
    public void setFridge(Fridge fridge) {
        mFridge = fridge;
    }
    
    public Fridge getFridge() {
        return mFridge;
    }

}
