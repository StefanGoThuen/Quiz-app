package com.example.oblig1.recyclerview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class DatabaseItem {

    private String name;
    private Bitmap image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public DatabaseItem(String name, Bitmap image){
        this.name = name;
        this.image = image;
    }
}
