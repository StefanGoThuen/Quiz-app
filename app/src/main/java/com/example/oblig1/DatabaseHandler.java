package com.example.oblig1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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

    static void getQuizItems(Context context, ArrayList<DatabaseItem> quizItems){
        SharedPreferences pref = context.getSharedPreferences("names", MODE_PRIVATE);
        Map allprefs = pref.getAll();
        Object[] strings = allprefs.values().toArray();
        HashMap<String, Bitmap> map = ImageHandler.retrieveImageWithName(context, getArrayList(strings));
        for(Object s: strings){
            if(s.toString().contains("_")){
                Log.i("imageremovestuff", "getQuizItems: " + s.toString());
                quizItems.add(new DatabaseItem(s.toString().split("_")[1], map.get(s.toString().split("_")[1]), s.toString()));
            }else{
                quizItems.add(new DatabaseItem(s.toString(), map.get(s.toString()), s.toString()));
            }
        }
        if(newUser(context)){
            addDefaultImages(quizItems, context);
        }

    }

    private static boolean newUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences("owner", MODE_PRIVATE);
        Boolean newUser = pref.getBoolean("new", true);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean("new", false);
        edit.commit();
        return newUser;
    }

    private static ArrayList<String> getArrayList(Object[] strings){
        ArrayList<String> list = new ArrayList<>();
        for(Object o : strings){
            list.add(o.toString());
        }
        return list;
    }

    /**
     * Metoden som legger til
     * bildene som allerede er laget
     */

    private static void addDefaultImages(ArrayList<DatabaseItem> quizItems, Context context){
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

        SharedPreferences pref = context.getSharedPreferences("names", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String s = ImageHandler.randomString() +"ole";
        ImageHandler.saveBitmapToFile(context, s, i1);
        editor.putString(s, s);
        s = ImageHandler.randomString() + "jostein";
        ImageHandler.saveBitmapToFile(context, s, i2);
        editor.putString(s, s);
        s = ImageHandler.randomString() + "petter";
        ImageHandler.saveBitmapToFile(context, s, i3);
        editor.putString(s, s);
        s = ImageHandler.randomString() + "simen";
        ImageHandler.saveBitmapToFile(context, s, i4);
        editor.putString(s, s);
        s = ImageHandler.randomString() + "stefan";
        ImageHandler.saveBitmapToFile(context, s, i5);
        editor.putString(s, s);
        editor.commit();

        DatabaseItem db1 = new DatabaseItem("ole", i1, "");
        DatabaseItem db2 = new DatabaseItem("jostein", i2, "");
        DatabaseItem db3 = new DatabaseItem("petter", i3, "");
        DatabaseItem db4 = new DatabaseItem("simen", i4, "");
        DatabaseItem db5 = new DatabaseItem("stefan", i5, "");
        quizItems.add(db1);
        quizItems.add(db2);
        quizItems.add(db3);
        quizItems.add(db4);
        quizItems.add(db5);
    }



}
