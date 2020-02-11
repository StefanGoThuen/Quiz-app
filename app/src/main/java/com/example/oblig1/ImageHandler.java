package com.example.oblig1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;

import java.io.ByteArrayOutputStream;

/**
 * Klasse som har kontroll over hvor bildene blir lagret og hvilket navn de har.
 */

public class ImageHandler {
    private static final String JPG = ".jpg";
    public static boolean justForTestDontDoThisInRealLifeThanks = false;

    /**
     * Metode som henter filnavnene fra de lagrete bildene fra minne
     */

    public static byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap scaledImage(Bitmap image) {
        Matrix m = new Matrix();
        if (image != null) {
            m.setRectToRect(new RectF(0, 0, image.getWidth(), image.getHeight()), new RectF(0, 0, 700, 700), Matrix.ScaleToFit.CENTER);
            return Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), m, true);
        } else {
            return null;
        }

    }
}
