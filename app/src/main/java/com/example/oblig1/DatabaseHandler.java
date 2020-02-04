package com.example.oblig1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;

import com.example.oblig1.recyclerview.DatabaseItem;
import com.example.oblig1.room.AppDatabase;
import com.example.oblig1.room.QuizItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Klasse som henter bilder og navn allerede lagret i databasen
 */

class DatabaseHandler {

    private static final String DHB = "DHB";

    static ArrayList<DatabaseItem> getQuizItems(Context context) {
        SharedPreferences pref = context.getSharedPreferences(DHB, Context.MODE_PRIVATE);
        if (!pref.getBoolean(DHB, false)) {
            handleDefaultImages(context);
        }
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "quiz").build();
        ArrayList<QuizItem> quizItems = (ArrayList<QuizItem>) db.quizDao().getAll();
        ArrayList<String> names = new ArrayList<>();
        for(QuizItem item : quizItems){
            names.add(item.getBitmapPath());
        }
        ArrayList<DatabaseItem> dbItems = new ArrayList<>();
        HashMap<String, Bitmap> map = ImageHandler.retrieveImageWithName(context, names);
        int count = 0;
        for(String key : map.keySet()){
            dbItems.add(new DatabaseItem(key.replaceAll("-", " "), map.get(key), quizItems.get(count).getBitmapPath()));
            count++;
        }
        return dbItems;
    }


    /**
     * Metoden som legger til
     * bildene som allerede er laget
     */

    private static void handleDefaultImages(Context context) {
        ArrayList<QuizItem> list = new ArrayList<>();
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
        QuizItem db1 = new QuizItem(0, "ole", "_ole");
        QuizItem db2 = new QuizItem(0, "jostein", "_jostein");
        QuizItem db3 = new QuizItem(0, "petter", "_petter");
        QuizItem db4 = new QuizItem(0, "simen", "_simen");
        QuizItem db5 = new QuizItem(0, "stefan", "_stefan");
        list.add(db1);
        list.add(db2);
        list.add(db3);
        list.add(db4);
        list.add(db5);
        ImageHandler.saveBitmapToFile(context, db1.getBitmapPath(), i1);
        ImageHandler.saveBitmapToFile(context, db2.getBitmapPath(), i2);
        ImageHandler.saveBitmapToFile(context, db3.getBitmapPath(), i3);
        ImageHandler.saveBitmapToFile(context, db4.getBitmapPath(), i4);
        ImageHandler.saveBitmapToFile(context, db5.getBitmapPath(), i5);
        saveImagesToStorage(context, list);
    }

    private static void saveImagesToStorage(Context context, ArrayList<QuizItem> list) {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "quiz").build();
        db.quizDao().insertAll(list);
        SharedPreferences pref = context.getSharedPreferences(DHB, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(DHB, true);
        edit.apply();
    }


}
