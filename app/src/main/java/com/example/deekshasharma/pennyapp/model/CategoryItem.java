package com.example.deekshasharma.pennyapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryItem implements Parcelable{

    private String name;
    private String id;
    private String groupName;

    public CategoryItem(String name, String id, String groupName)
    {
        this.name = name;
        this.id = id;
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
