package com.example.oblig1.recyclerview;

import android.graphics.drawable.Drawable;

public class DatabaseItem {

    private String name;
    private Drawable image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public DatabaseItem(String name, Drawable image){
        this.name = name;
        this.image = image;
    }
}
