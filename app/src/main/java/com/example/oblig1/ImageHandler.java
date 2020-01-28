package com.example.oblig1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class ImageHandler {
    private static final String JPG = ".jpg";


    static HashMap<String, Bitmap> retrieveImageWithName(Context context, ArrayList<String> filenames) {
        HashMap<String, Bitmap> map = new HashMap<>();
        for (String name : filenames) {
            File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File imgFile = new File(storageDir + "/" + name + JPG + "/");
            if (imgFile.exists()) {
                System.out.println(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
                map.put(name.split("_")[1], BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
            }
        }
        return map;
    }

    static void saveBitmapToFile(Context context, String imageName, Bitmap bitmap) {
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
    static String randomString() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        char tempChar;
        for (int i = 0; i < 10; i++) {
            tempChar = (char) (generator.nextInt('z' - 'a') + 'a');
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString()+"_";
    }

    public static boolean removeImageFromStorage(Context context, String name){
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.i("imageremovestuff", "removeImageFromStorage: "+name);
        File imgFile = new File(storageDir + "/" + name + JPG + "/");
        return imgFile.delete();
    }

}
