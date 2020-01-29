package com.example.oblig1.recyclerview;

import android.graphics.Bitmap;

import com.example.oblig1.ImageHandler;

/**
 * Klasse for getters og setter som blir hentet i RecyclerViewAdapter klassene
 */
public class DatabaseItem {

    private String name;
    private Bitmap image;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

    public DatabaseItem(String name, Bitmap image, String fileName) {
        Bitmap scaledImage = ImageHandler.scaledImage(image);
        this.name = name;
        this.image = scaledImage;
        this.fileName = fileName;
    }
}
