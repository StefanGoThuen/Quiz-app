package com.example.oblig1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class ImageHandler {
    private static final String JPG = ".jpg";


    public static HashMap<Bitmap, String> retrieveImageWithName(Context context, ArrayList<String> filenames) {
        HashMap<Bitmap, String> map = new HashMap<>();
        for (String name : filenames) {
            File imgFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + name);
            if (imgFile.exists()) {
                map.put(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), name.split("_")[1].split(".")[0]);
            }
        }
        return map;
    }

    public static void saveBitmapToFile(Context context, String imageName, Bitmap bitmap) {
        // Image should be stored locally
        String storagePath = Environment.DIRECTORY_PICTURES;
        File storageDir = context.getExternalFilesDir(storagePath);

        if (storageDir != null && !storageDir.exists()) {
            File dir = new File(storagePath);
            if (!dir.mkdirs()) {
                return;
            }
        }
        File image = new File(
                storageDir + "/" + imageName +JPG
        );
        try {
            FileOutputStream out = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out); // bmp is your Bitmap instance
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String randomString() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        char tempChar;
        for (int i = 0; i < 10; i++) {
            tempChar = (char) (generator.nextInt('z' - 'a') + 'a');
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString()+"_";
    }
}
