package com.fridgi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ObjectID implements Parcelable {
    
    @SerializedName("$oid") private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public ObjectID(Parcel in) {
        id = in.readString();
    }
    
    public static final Parcelable.Creator<ObjectID> CREATOR = new Parcelable.Creator<ObjectID>() {
        public ObjectID createFromParcel(Parcel in) {
            return new ObjectID(in);
        }

        @Override 
        public ObjectID[] newArray(int size) {
            return new ObjectID[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
    }

}
