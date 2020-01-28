package com.example.oblig1.recyclerview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
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
        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, image.getWidth(), image.getHeight()), new RectF(0, 0, 700, 700), Matrix.ScaleToFit.CENTER);
        Bitmap scaledImage = Bitmap.createBitmap(image, 0, 0,  image.getWidth(),  image.getHeight(), m, true);
        this.name = name;
        this.image = scaledImage;
    }
}
