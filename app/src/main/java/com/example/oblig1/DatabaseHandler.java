package com.example.oblig1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.oblig1.recyclerview.DatabaseItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Klasse som henter bilder og navn allerede lagret i databasen
 */

class DatabaseHandler {

    private static final String DHB = "DHB";

    static void getQuizItems(Context context, ArrayList<DatabaseItem> quizItems) {
        SharedPreferences pref = context.getSharedPreferences(DHB, MODE_PRIVATE);
        if(!pref.getBoolean(DHB, false)){
            handleDefaultImages(context);
        }
        pref = context.getSharedPreferences("names", MODE_PRIVATE);
        Map allprefs = pref.getAll();
        Object[] strings = allprefs.values().toArray();
        HashMap<String, Bitmap> map = ImageHandler.retrieveImageWithName(context, getArrayList(strings));
        for (Object s : strings) {
            if (s.toString().contains("_")) {
                Log.i("imageremovestuff", "getQuizItems: " + s.toString());
                quizItems.add(new DatabaseItem(s.toString().split("_")[1], map.get(s.toString().split("_")[1]), s.toString()));
            } else {
                quizItems.add(new DatabaseItem(s.toString(), map.get(s.toString()), s.toString()));
            }
        }
    }

    private static ArrayList<String> getArrayList(Object[] strings) {
        ArrayList<String> list = new ArrayList<>();
        for (Object o : strings) {
            list.add(o.toString());
        }
        return list;
    }

    /**
     * Metoden som legger til
     * bildene som allerede er laget
     */

    private static void handleDefaultImages(Context context) {
        ArrayList<DatabaseItem> list = new ArrayList<>();
        Bitmap i1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ole);
        Bitmap i2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.jostein);
        Bitmap i3 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.petter);
        Bitmap i4 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.simen);
        Bitmap i5 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.stefan);
        DatabaseItem db1 = new DatabaseItem("ole", i1, "");
        DatabaseItem db2 = new DatabaseItem("jostein", i2, "");
        DatabaseItem db3 = new DatabaseItem("petter", i3, "");
        DatabaseItem db4 = new DatabaseItem("simen", i4, "");
        DatabaseItem db5 = new DatabaseItem("stefan", i5, "");
        list.add(db1);
        list.add(db2);
        list.add(db3);
        list.add(db4);
        list.add(db5);
        saveImagesToStorage(context, list);
    }

    private static void saveImagesToStorage(Context context, ArrayList<DatabaseItem> list) {
        SharedPreferences pref = context.getSharedPreferences("names", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        for (int i = 0; i < list.size(); i++) {
            String name = "_"+list.get(i).getName();
            editor.putString(name, name);
            ImageHandler.saveBitmapToFile(context, name, list.get(i).getImage());
            editor.apply();
        }
        pref = context.getSharedPreferences(DHB, MODE_PRIVATE);
        editor = pref.edit();
        editor.putBoolean(DHB, true);
        editor.apply();
    }


}
